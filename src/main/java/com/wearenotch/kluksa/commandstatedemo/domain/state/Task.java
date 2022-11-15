package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import org.jetbrains.annotations.NotNull;

public abstract class Task {

  private final TaskDataContext context;

  protected Task(Status status, TaskDataContext context) {
    this.context = context;
    context.setStatus(status);
  }

  public abstract Task cancel();

  public abstract Task complete();

  public abstract Task approve();

  public abstract Task reject();

  public abstract Task makeReady();

  protected TaskDataContext getContext(){
    return context;
  }

  public TaskDetailsDto toDto() {
    return new TaskDetailsDto(context.getId(), context.getTitle(), context.getApproved(), context.getStatus());
  }

  public Task setTitle(@NotNull final String title) {
    context.setTitle(title);
    return this;
  }

  public enum Status {
    ACTIVE, READY, ALMOST_READY, FINISHED, CANCELED
  }
}
