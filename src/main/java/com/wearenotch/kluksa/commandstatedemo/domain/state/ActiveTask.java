package com.wearenotch.kluksa.commandstatedemo.domain.state;


import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import org.jetbrains.annotations.NotNull;

class ActiveTask extends AbstractTask {

    protected ActiveTask(@NotNull TaskDataContext ctx) {
        super(ctx);
    }

    @Override
    public @NotNull Task cancel() {
        return Status.CANCELED.createTask(getContext());
    }

    @Override
    public @NotNull Task approve() {
        getContext().setApproved(true);
        return Status.READY.createTask(getContext());
    }

    @Override
    public @NotNull Task reject() {
        getContext().setApproved(false);
        return Status.NOT_READY.createTask(getContext());
    }
}
