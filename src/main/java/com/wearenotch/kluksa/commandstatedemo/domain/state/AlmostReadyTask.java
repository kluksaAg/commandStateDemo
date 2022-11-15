package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.domain.events.StateChangeEvent;

public class AlmostReadyTask extends Task {

  public AlmostReadyTask(TaskDataContext ctx) {
    super(Status.READY, ctx);
    ctx.publish(new StateChangeEvent(this.getClass().getName()));
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
