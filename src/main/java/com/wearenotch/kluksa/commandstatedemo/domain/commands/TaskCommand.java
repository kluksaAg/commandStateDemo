package com.wearenotch.kluksa.commandstatedemo.domain.commands;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import org.jetbrains.annotations.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "cmd")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChangeTitleCommand.class, name = "change-title"),
        @JsonSubTypes.Type(value = ApproveCommand.class, name = "approve"),
        @JsonSubTypes.Type(value = RejectCommand.class, name = "reject"),
        @JsonSubTypes.Type(value = CancelTaskCommand.class, name = "cancel"),
        @JsonSubTypes.Type(value = CompleteTaskCommand.class, name = "complete")
})
public interface TaskCommand {

    @NotNull Task execute(@NotNull final Task task);

}
