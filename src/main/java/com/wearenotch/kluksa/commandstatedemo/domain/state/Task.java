package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public interface Task {

    @NotNull Task cancel();

    @NotNull Task complete();

    @NotNull Task approve();

    @NotNull Task reject();

    @NotNull Task setTitle(@NotNull final String title);

    @NotNull TaskDetailsDto toDto();

    enum Status {
        ACTIVE(ActiveTask::new),
        READY(ReadyTask::new),
        NOT_READY(NotReadyTask::new),
        COMPLETED(CompletedTask::new),
        CANCELED(CanceledTask::new);

        private final @NotNull Function<TaskDataContext, Task> factory;

        Status(@NotNull final Function<TaskDataContext, Task> factory) {
            this.factory = factory;
        }

        public Task createTask(@NotNull final TaskDataContext tdc) {
            tdc.setStatus(this);
            return factory.apply(tdc);
        }
    }
}
