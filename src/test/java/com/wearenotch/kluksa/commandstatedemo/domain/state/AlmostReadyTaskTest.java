package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
class AlmostReadyTaskTest {
  private Task task;

  @BeforeEach
  public void setup(){
    var ctx = new TaskDataContext(new TaskEntity().setTitle("TITLE"));
    this.task = new AlmostReadyTask(ctx);
  }

  @Test
  void newTask(){
    assertEquals(task.getContext().getStatus(), Task.Status.ALMOST_READY);
  }


  @Test
  void cancel() {
    final Task newState = task.cancel();
    assertInstanceOf(CanceledTask.class, newState);
  }

  @Test
  void complete() {
    final Task newState = task.complete();
    assertEquals(task, newState);
  }

  @Test
  void approve() {
    final Task newState = task.approve();
    assertEquals(task, newState);
  }

  @Test
  void reject() {
    final Task newState = task.reject();
    assertEquals(task, newState);
  }

  @Test
  void makeReady() {
    final Task newState = task.makeReady();
    assertInstanceOf(ReadyToCompleteTask.class, newState);
  }

  @Test
  void changeTitle() {
    final String new_title = "NEW TITLE";
    task.setTitle(new_title);
    assertEquals(new_title, task.getContext().getTitle());
  }
}
