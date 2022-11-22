package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import org.jetbrains.annotations.NotNull;

public class FinishedTask extends Task {

  public FinishedTask(TaskDataContext ctx) {
    super(Status.FINISHED, ctx);
  }

  @Override
  public Task cancel() {
    throw new IllegalStateException();
  }

  @Override
  public Task complete() {
    throw new IllegalStateException();
  }

  @Override
  public Task approve() {
    throw new IllegalStateException();
  }

  @Override
  public Task reject() {
    throw new IllegalStateException();
  }

  @Override
  public Task makeReady() {
    throw new IllegalStateException();
  }

  @Override
  public Task setTitle(@NotNull final String title) {
    throw new IllegalStateException();
  }

}
