package com.wearenotch.kluksa.commandstatedemo.domain.commands;

import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import org.jetbrains.annotations.NotNull;

public class RejectCommand extends AbstractTaskCommand {

  @Override
  public Task execute(@NotNull final Task task) {
    return task.reject();
  }
}
