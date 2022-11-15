package com.wearenotch.kluksa.commandstatedemo.domain.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

public class ChangeTitleCommand extends AbstractTaskCommand {

  @NotBlank private final String title;

  @JsonCreator
  public ChangeTitleCommand(@NotBlank @JsonProperty("title") final String title) {
    this.title = title;
  }

  @Override
  public Task execute(@NotNull final Task task) {
    return task.setTitle(title);
  }
}
