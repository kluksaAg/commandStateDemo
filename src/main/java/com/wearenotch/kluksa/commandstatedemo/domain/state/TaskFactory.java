package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import com.wearenotch.kluksa.commandstatedemo.persistence.repository.TaskEntityRepository;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskSubmitDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TaskFactory {

  private final TaskEntityRepository repository;
  private final ApplicationEventPublisher publisher;

  public TaskFactory(final TaskEntityRepository repository,
                     final ApplicationEventPublisher eventPublisher) {
    this.repository = repository;
    this.publisher = eventPublisher;
  }

  public Task from(@NotNull final TaskSubmitDto dto) {
    TaskEntity entity = createActiveTaskEntity(dto);
    final TaskEntity savedEntity = repository.save(entity);
    return from(savedEntity);
  }

  @NotNull
  private TaskEntity createActiveTaskEntity(@NotNull TaskSubmitDto dto) {
    return new TaskEntity()
        .setTitle(dto.getTitle())
        .setStatus(Task.Status.ACTIVE);
  }

  public Task from(@NotNull final TaskEntity entity){
    TaskDataContext ctx = new TaskDataContext(publisher, entity);
    return getTask(entity, ctx);
  }

  @NotNull
  private Task getTask(@NotNull TaskEntity entity, TaskDataContext ctx) {
    return switch (entity.getStatus()) {
      case ACTIVE -> new ActiveTask(ctx);
      case READY -> new ReadyToCompleteTask(ctx);
      case ALMOST_READY -> new AlmostReadyTask(ctx);
      case FINISHED -> new FinishedTask(ctx);
      case CANCELED -> new CanceledTask(ctx);
    };
  }
}
