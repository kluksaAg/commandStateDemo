package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FinishedTaskTest {
  @Mock
  private ApplicationEventPublisher publisher;
  private Task task;

  @BeforeEach
  public void setup(){
    var ctx = new TaskDataContext(publisher, new TaskEntity().setTitle("TITLE"));
    this.task = new FinishedTask(ctx);
  }

  @Test
  void newTask(){
    assertEquals(task.getContext().getStatus(), Task.Status.FINISHED);
  }


  @Test
  void cancel() {
    assertThrows(UnsupportedOperationException.class, ()->task.cancel());
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
    assertEquals(task, newState);
  }

  @Test
  void changeTitle() {
    final String new_title = "NEW TITLE";
    assertThrows(UnsupportedOperationException.class, ()->task.setTitle(new_title));
  }
}
