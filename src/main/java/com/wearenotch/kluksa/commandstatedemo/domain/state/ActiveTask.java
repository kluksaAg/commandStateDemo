package com.wearenotch.kluksa.commandstatedemo.domain.state;


import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.domain.events.StateChangeEvent;

public class ActiveTask extends Task {

  public ActiveTask(TaskDataContext ctx) {
    super(Status.ACTIVE, ctx);
    ctx.publish(new StateChangeEvent(this.getClass().getName()));
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
  public Task makeReady() {
    return this;
  }

  @Override
  public Task approve() {
    getContext().setApproved(true);
    return new ReadyToCompleteTask(getContext());
  }

  @Override
  public Task reject() {
    getContext().setApproved(false);
    return new AlmostReadyTask(getContext());
  }
}
