package com.wearenotch.kluksa.commandstatedemo.domain.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

public record ChangeTitleCommand(@NotBlank @JsonProperty("title") String title) implements TaskCommand {

    @Override
    public @NotNull Task execute(@NotNull final Task task) {
        return task.setTitle(title);
    }
}
