package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private final Long id = 1L;
    private final String title = "TITLE";
    private TaskDataContext ctx;

    @BeforeEach
    void setUp() {
        this.ctx = new TaskDataContext(new TaskEntity().setId(id).setTitle(title).setApproved(true));
    }

    @Test
    void toDto() {
        TaskDataContext taskDataContext = new TaskDataContext(new TaskEntity().setStatus(Task.Status.ACTIVE).setId(id).setTitle(title).setApproved(true));
        TaskDetailsDto dto = taskDataContext.getStatus().createTask(taskDataContext).toDto();
        assertEquals(id, dto.getId());
        assertEquals(title, dto.getTitle());
        assertTrue(dto.isApproved());

        taskDataContext = new TaskDataContext(new TaskEntity().setStatus(Task.Status.ACTIVE).setId(id).setTitle(title).setApproved(false));
        dto = taskDataContext.getStatus().createTask(taskDataContext).toDto();
        assertEquals(id, dto.getId());
        assertEquals(title, dto.getTitle());
        assertFalse(dto.isApproved());
    }

    @ParameterizedTest
    @EnumSource(Task.Status.class)
    void toDto(Task.Status status) {
        final TaskDataContext taskDataContext = new TaskDataContext(new TaskEntity().setStatus(status).setId(id).setTitle(title).setApproved(true));
        final TaskDetailsDto dto = taskDataContext.getStatus().createTask(taskDataContext).toDto();
        assertEquals(status, dto.getStatus());
        assertEquals(id, dto.getId());
        assertEquals(title, dto.getTitle());
        assertTrue(dto.isApproved());
    }

    @Test
    void setTitle() {
        final AbstractTask task = (AbstractTask) Task.Status.ACTIVE.createTask(ctx);
        assertEquals("TITLE", task.getContext().getTitle());
        task.setTitle("T1");
        assertEquals("T1", task.getContext().getTitle());
    }
}
