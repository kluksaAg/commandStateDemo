package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CanceledTaskTest {
  private Task task;

  @BeforeEach
  public void setup(){
    var ctx = new TaskDataContext(new TaskEntity().setTitle("TITLE"));
    this.task = new CanceledTask(ctx);
  }

  @Test
  void newTask(){
    assertEquals(task.getContext().getStatus(), Task.Status.CANCELED);
  }


  @Test
  void cancel() {
    assertThrows(IllegalStateException.class, task::cancel);
  }

  @Test
  void complete() {
    assertThrows(IllegalStateException.class, task::cancel);
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
