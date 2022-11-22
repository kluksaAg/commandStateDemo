package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;

public class ReadyToCompleteTask extends Task {

  public ReadyToCompleteTask(TaskDataContext ctx) {
    super(Status.READY, ctx);
  }

  @Override
  public Task cancel() {
    return new CanceledTask(getContext());
  }

  @Override
  public Task complete() {
    return new FinishedTask(getContext());
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

}
