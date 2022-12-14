package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ActiveTaskTest {

    private AbstractTask task;

    @BeforeEach
    public void setup() {
        var ctx = new TaskDataContext(new TaskEntity().setTitle("TITLE"));
        this.task = (AbstractTask) Task.Status.ACTIVE.createTask(ctx);
    }

    @Test
    void newTask() {
        assertEquals(task.getContext().getStatus(), Task.Status.ACTIVE);
    }

    @Test
    void cancel() {
        final Task newState = task.cancel();
        verifyNewState(newState, CanceledTask.class);
    }

    @Test
    void complete() {
        final AbstractTask newState = (AbstractTask) task.complete();
        assertEquals(task, newState);
    }

    @Test
    void approve() {
        final AbstractTask newState = (AbstractTask) task.approve();
        assertTrue(newState.getContext().getApproved());
        verifyNewState(newState, ReadyTask.class);
    }

    @Test
    void reject() {
        final AbstractTask newState = (AbstractTask) task.reject();
        assertFalse(newState.getContext().getApproved());
        verifyNewState(newState, NotReadyTask.class);
    }

    private void verifyNewState(Task newState, @NotNull Class<? extends Task> expectedClass) {
        assertInstanceOf(expectedClass, newState);
    }

    @Test
    void changeTitle() {
        final String new_title = "NEW TITLE";
        task.setTitle(new_title);
        assertEquals(new_title, task.getContext().getTitle());
    }
}
