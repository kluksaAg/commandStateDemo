package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import org.jetbrains.annotations.NotNull;

class CanceledTask extends AbstractTask {

    protected CanceledTask(@NotNull TaskDataContext ctx) {
        super(ctx);
    }

    @Override
    public @NotNull Task complete() {
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
