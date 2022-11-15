package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.domain.events.StateChangeEvent;

import org.jetbrains.annotations.NotNull;

public class FinishedTask extends Task {

  public FinishedTask(TaskDataContext ctx) {
    super(Status.FINISHED, ctx);
    ctx.publish(new StateChangeEvent(this.getClass().getName()));
  }

  @Override
  public Task cancel() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Task complete() {
    return this;
  }

  @Override
  public Task approve() {
    return this;
  }

  @Override
  public Task reject() {
    return this;
  }

  @Override
  public Task makeReady() {
    return this;
  }

  @Override
  public Task setTitle(@NotNull final String title) {
    throw new UnsupportedOperationException();
  }

}
