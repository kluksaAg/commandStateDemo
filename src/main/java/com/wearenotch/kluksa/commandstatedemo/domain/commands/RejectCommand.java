package com.wearenotch.kluksa.commandstatedemo.domain.commands;

import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import org.jetbrains.annotations.NotNull;

public record RejectCommand() implements TaskCommand {

    @Override
    public @NotNull Task execute(@NotNull final Task task) {
        return task.reject();
    }
}
