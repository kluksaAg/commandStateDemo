package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;

public class AlmostReadyTask extends Task {

  public AlmostReadyTask(TaskDataContext ctx) {
    super(Status.ALMOST_READY, ctx);
  }

  @Override
  public Task makeReady() {
    return new ReadyToCompleteTask(getContext());
  }

  @Override
  public Task cancel() {
    return new CanceledTask(getContext());
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
}
