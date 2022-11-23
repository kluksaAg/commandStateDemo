package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractTask implements Task {

    @NotNull
    private final TaskDataContext context;

    protected AbstractTask(@NotNull final TaskDataContext context) {
        this.context = context;
    }

    //
    // Defaultno ponašanje je da nema nikakvih tranzicija
    //
    @NotNull
    public Task cancel() {
        return this;
    }

    @NotNull
    public Task complete() {
        return this;
    }

    @NotNull
    public Task approve() {
        return this;
    }

    @NotNull
    public Task reject() {
        return this;
    }

    @NotNull
    protected TaskDataContext getContext() {
        return context;
    }

    @NotNull
    public Task setTitle(@NotNull final String title) {
        context.setTitle(title);
        return this;
    }

    @NotNull
    public TaskDetailsDto toDto() {
        return new TaskDetailsDto(context.getId(), context.getTitle(), context.getApproved(), context.getStatus());
    }

}
