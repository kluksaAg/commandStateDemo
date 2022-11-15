package com.wearenotch.kluksa.commandstatedemo.service;

import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import com.wearenotch.kluksa.commandstatedemo.domain.state.TaskFactory;
import com.wearenotch.kluksa.commandstatedemo.persistence.repository.TaskEntityRepository;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskSubmitDto;
import com.wearenotch.kluksa.commandstatedemo.domain.commands.AbstractTaskCommand;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class TaskService {
    private final TaskEntityRepository taskRepo;
    private final TaskFactory taskFactory;


    public TaskService(final TaskEntityRepository taskRepo,
                       final TaskFactory taskFactory) {
        this.taskRepo = taskRepo;
        this.taskFactory = taskFactory;
    }

    @NotNull
    public Optional<TaskDetailsDto> getTaskDetails(@NotNull final Long id) {
        return getTask(id)
            .map(Task::toDto);
    }

    @NotNull
    public List<TaskDetailsDto> getTasks() {
        return taskRepo.findAll()
            .stream()
            .map(taskFactory::from)
            .map(Task::toDto)
            .toList();
    }

    @NotNull
    public TaskDetailsDto submit(@NotNull final TaskSubmitDto submitDto) {
        final Task task = taskFactory.from(submitDto);
        return task.toDto();
    }

    @NotNull
    public Optional<TaskDetailsDto> executeCommand(@NotNull final Long taskId,
                                                   @NotNull final AbstractTaskCommand command) {
        return getTask(taskId)
            .map(command::execute)
            .map(Task::toDto);
    }

    @NotNull
    private Optional<Task> getTask(@NotNull final Long id) {
        return taskRepo.findById(id)
            .map(taskFactory::from);
    }
}
