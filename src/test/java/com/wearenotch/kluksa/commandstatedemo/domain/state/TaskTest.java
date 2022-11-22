package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
  private TaskDataContext ctx;
  private Task task;
  private final Long id = 1L;
  private final String title = "TITLE";

  @BeforeEach
  void setUp() {
    this.ctx = new TaskDataContext(new TaskEntity().setId(id).setTitle(title).setApproved(true));
    this.task = getTask(Task.Status.ACTIVE, ctx);
  }

  @NotNull
  private Task getTask(final Task.Status active, final TaskDataContext ctx) {
    return new Task(active, ctx) {
      @Override
      public Task cancel() {
        return null;
      }

      @Override
      public Task complete() {
        return null;
      }

      @Override
      public Task approve() {
        return null;
      }

      @Override
      public Task reject() {
        return null;
      }

      @Override
      public Task makeReady() {
        return null;
      }
    };
  }

  @Test
  void getContext() {
    assertEquals(ctx, task.getContext());
  }

  @Test
  void toDto(){
    TaskDataContext taskDataContext = new TaskDataContext(new TaskEntity().setId(id).setTitle(title).setApproved(true));
    TaskDetailsDto dto = getTask(null, taskDataContext).toDto();
    assertEquals(id, dto.getId());
    assertEquals(title, dto.getTitle());
    assertTrue(dto.isApproved());

    taskDataContext = new TaskDataContext(new TaskEntity().setId(id).setTitle(title).setApproved(false));
    dto = getTask(null, taskDataContext).toDto();
    assertEquals(id, dto.getId());
    assertEquals(title, dto.getTitle());
    assertFalse(dto.isApproved());
  }

  @ParameterizedTest
  @EnumSource(Task.Status.class)
  void toDto(Task.Status status) {
    final TaskDataContext taskDataContext = new TaskDataContext(new TaskEntity().setId(id).setTitle(title).setApproved(true));
    final TaskDetailsDto dto = getTask(status, taskDataContext).toDto();
    assertEquals(status, dto.getStatus());
    assertEquals(id, dto.getId());
    assertEquals(title, dto.getTitle());
    assertTrue(dto.isApproved());
  }

  @Test
  void setTitle() {
    assertEquals("TITLE", task.getContext().getTitle());
    task.setTitle("T1");
    assertEquals("T1", task.getContext().getTitle());
  }
}
