package com.wearenotch.kluksa.commandstatedemo.service;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.domain.commands.TaskCommand;
import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import com.wearenotch.kluksa.commandstatedemo.persistence.repository.TaskEntityRepository;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskSubmitDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    private final TaskEntityRepository taskRepo;


    public TaskService(final TaskEntityRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    @NotNull
    public Optional<TaskDetailsDto> getTaskDetails(@NotNull final Long id) {
        return getTask(id)
                .map(Task::toDto);
    }

    @NotNull
    public List<TaskDetailsDto> getTasks() {
        return taskRepo.findAll().stream()
                .map(this::from)
                .map(Task::toDto)
                .toList();
    }

    @NotNull
    public TaskDetailsDto submit(@NotNull final TaskSubmitDto dto) {
        final TaskEntity entity = createActiveTaskEntity(dto);
        final Task task = from(taskRepo.save(entity));
        return task.toDto();
    }

    @NotNull
    public Optional<TaskDetailsDto> executeCommand(@NotNull final Long id,
                                                   @NotNull final TaskCommand command) {
        return getTask(id)
                .map(command::execute)
                .map(Task::toDto);
    }

    @NotNull
    private Optional<Task> getTask(@NotNull final Long id) {
        return taskRepo.findById(id)
                .map(this::from);
    }

    @NotNull
    private TaskEntity createActiveTaskEntity(@NotNull TaskSubmitDto dto) {
        return new TaskEntity()
                .setTitle(dto.getTitle())
                .setStatus(Task.Status.ACTIVE);
    }

    private Task from(@NotNull final TaskEntity entity) {
        TaskDataContext ctx = new TaskDataContext(entity);
        final Task.Status status = entity.getStatus();
        return status.createTask(ctx);
    }
}
