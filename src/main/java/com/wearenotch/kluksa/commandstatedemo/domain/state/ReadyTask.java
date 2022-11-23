package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import org.jetbrains.annotations.NotNull;

class ReadyTask extends AbstractTask {

    protected ReadyTask(@NotNull TaskDataContext ctx) {
        super(ctx);
    }

    @Override
    public @NotNull Task cancel() {
        return Status.CANCELED.createTask(getContext());
    }

    @Override
    public @NotNull Task complete() {
        return Status.COMPLETED.createTask(getContext());
    }
}
