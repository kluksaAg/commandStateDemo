package com.wearenotch.kluksa.commandstatedemo.web.rest;

import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import com.wearenotch.kluksa.commandstatedemo.persistence.repository.TaskEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TasksResourceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TaskEntityRepository repo;



  @Test
  void getTask() throws Exception {
    final TaskEntity entity = new TaskEntity()
        .setTitle("TAJTL")
        .setStatus(Task.Status.READY)
        .setApproved(true);
    final TaskEntity taskEntity = repo.saveAndFlush(entity);
    mockMvc.perform(get("/tasks/" + taskEntity.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(taskEntity.getId()))
        .andExpect(jsonPath("$.title").value("TAJTL"))
        .andExpect(jsonPath("$.approved").value(true))
        .andExpect(jsonPath("$.status").value("READY"))
    ;
  }

  @Test
  void getAllTasks() throws Exception {
    repo.saveAndFlush(new TaskEntity()
        .setTitle("TAJTL1")
        .setStatus(Task.Status.READY)
        .setApproved(true));
    repo.saveAndFlush(new TaskEntity()
        .setTitle("TAJTL2")
        .setStatus(Task.Status.ALMOST_READY)
        .setApproved(true));
    repo.saveAndFlush(new TaskEntity()
        .setTitle("TAJTL3")
        .setStatus(Task.Status.READY)
        .setApproved(false));
    mockMvc.perform(get("/tasks/")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)));
  }

  @Test
  void submitTask() throws Exception {
    mockMvc.perform(post("/tasks/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"title\": \"TITLE\"}")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.title").value("TITLE"))
        .andExpect(jsonPath("$.approved").isEmpty())
        .andExpect(jsonPath("$.status").value("ACTIVE"))
    ;
  }

  @Test
  void sendCommandToTask() {
  }
}
