package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import org.jetbrains.annotations.NotNull;

class NotReadyTask extends AbstractTask {

    protected NotReadyTask(@NotNull TaskDataContext ctx) {
        super(ctx);
    }

    @Override
    public @NotNull Task approve() {
        return Status.ACTIVE.createTask(getContext()).approve();
    }

    @Override
    public @NotNull Task cancel() {
        return Status.CANCELED.createTask(getContext());
    }
}
