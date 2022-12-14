package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CanceledTaskTest {
    private Task task;

    @BeforeEach
    public void setup() {
        var ctx = new TaskDataContext(new TaskEntity().setTitle("TITLE"));
        this.task = Task.Status.CANCELED.createTask(ctx);
    }

    @Test
    void newTask() {
        assertEquals(((AbstractTask) task).getContext().getStatus(), Task.Status.CANCELED);
    }

    @Test
    void cancel() {
        final Task newState = task.cancel();
        assertInstanceOf(CanceledTask.class, newState);
    }

    @Test
    void complete() {
        assertThrows(IllegalStateException.class, task::complete);
    }

    @Test
    void approve() {
        assertThrows(IllegalStateException.class, task::approve);
    }

    @Test
    void reject() {
        assertThrows(IllegalStateException.class, task::reject);
    }

    @Test
    void changeTitle() {
        assertThrows(IllegalStateException.class, () -> task.setTitle("TITLE"));
    }
}
