package com.wearenotch.kluksa.commandstatedemo.domain.state;

import com.wearenotch.kluksa.commandstatedemo.domain.TaskDataContext;
import com.wearenotch.kluksa.commandstatedemo.domain.events.StateChangeEvent;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class ActiveTaskTest {

  @Mock
  private ApplicationEventPublisher publisher;
  private Task task;

  @BeforeEach
  public void setup(){
    var ctx = new TaskDataContext(publisher, new TaskEntity().setTitle("TITLE"));
    this.task = new ActiveTask(ctx);
  }

  @Test
  void newTask(){
    assertEquals(task.getContext().getStatus(), Task.Status.ACTIVE);
  }

  @Test
  void cancel() {
    final Task newState = task.cancel();
    verifyNewState(newState, CanceledTask.class);
  }

  @Test
  void complete() {
    final Task newState = task.complete();
    assertEquals(task, newState);
  }

  @Test
  void approve() {
    final Task newState = task.approve();
    assertTrue(newState.getContext().getApproved());
    verifyNewState(newState, ReadyToCompleteTask.class);
  }

  @Test
  void reject() {
    final Task newState = task.reject();
    assertFalse(newState.getContext().getApproved());
    verifyNewState(newState, AlmostReadyTask.class);
  }

  @Test
  void makeReady() {
    final Task newState = task.makeReady();
    assertEquals(task, newState);
  }

  private void verifyNewState(Task newState, Class<? extends  Task> expectedClass) {
    assertInstanceOf(expectedClass, newState);
    Mockito.verify(publisher).publishEvent(
        argThat((ArgumentMatcher<StateChangeEvent>) event -> event.getStateName().equals(expectedClass.getName())));
  }

  @Test
  void changeTitle() {
    final String new_title = "NEW TITLE";
    task.setTitle(new_title);
    assertEquals(new_title, task.getContext().getTitle());
  }
}
