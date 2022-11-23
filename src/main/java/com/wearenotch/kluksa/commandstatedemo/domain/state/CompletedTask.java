package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import org.jetbrains.annotations.NotNull;

class CompletedTask extends AbstractTask {

    protected CompletedTask(@NotNull TaskDataContext ctx) {
        super(ctx);
    }

    @Override
    public @NotNull Task cancel() {
        throw new IllegalStateException();
    }

    @Override
    public @NotNull Task approve() {
        throw new IllegalStateException();
    }

    @Override
    public @NotNull Task reject() {
        throw new IllegalStateException();
    }

    @Override
    public @NotNull Task setTitle(@NotNull final String title) {
        throw new IllegalStateException();
    }

}
