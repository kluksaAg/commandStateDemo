package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompletedTaskTest {
    private Task task;

    @BeforeEach
    public void setup() {
        var ctx = new TaskDataContext(new TaskEntity().setTitle("TITLE"));
        this.task = Task.Status.COMPLETED.createTask(ctx);
    }

    @Test
    void newTask() {
        assertEquals(((AbstractTask) task).getContext().getStatus(), Task.Status.COMPLETED);
    }


    @Test
    void cancel() {
        assertThrows(IllegalStateException.class, task::cancel);
    }

    @Test
    void complete() {
        final Task newState = task.complete();
        assertInstanceOf(CompletedTask.class, newState);
    }

    @Test
    void approve() {
        assertThrows(IllegalStateException.class, task::cancel);
    }

    @Test
    void reject() {
        assertThrows(IllegalStateException.class, task::cancel);
    }

    @Test
    void makeReady() {
        assertThrows(IllegalStateException.class, task::cancel);
    }

    @Test
    void changeTitle() {
        assertThrows(IllegalStateException.class, task::cancel);
    }
}
