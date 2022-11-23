package com.wearenotch.kluksa.commandstatedemo.cucumber.stepdefs;

import com.wearenotch.kluksa.commandstatedemo.CommandStateDemoApplication;
import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import com.wearenotch.kluksa.commandstatedemo.persistence.repository.TaskEntityRepository;
import com.wearenotch.kluksa.commandstatedemo.web.rest.TasksResource;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CommandStateDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@AutoConfigureMockMvc
public class TaskStepDefs {


    private static final String TITLE = "Tajtl";

    @Autowired
    private TasksResource userResource;
    @Autowired
    private TaskEntityRepository repository;
    @Autowired
    private MockMvc mockMvc;
    private @Nullable Exception lastException;
    private Task.Status status;
    private ResultActions actions;
    private TaskEntity entity;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
    }

    @BeforeEach
    public void clear() {
        this.lastException = null;
    }

    @When("I POST task dto with title {string}")
    public void i_post_dto(String title) {
        try {
            actions = mockMvc.perform(post("/tasks/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"title\": \"" + title + "\"}")
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception ex) {
            this.lastException = ex;
        }
    }

    @Then("New ACTIVE task is created with title {string}")
    public void the_task_is_created(String title) throws Throwable {
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.approved").isEmpty())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Then("task is not created")
    public void the_task_is_not_created() {
        assertNull(actions);
    }

    @Given("task is in status {string}")
    public void create_task_in_status(String statusString) {
        status = Task.Status.valueOf(statusString);
        entity = new TaskEntity()
                .setTitle(TITLE)
                .setStatus(status);
        if (status == Task.Status.READY || status == Task.Status.COMPLETED)
            entity.setApproved(true);
        if (status == Task.Status.NOT_READY)
            entity.setApproved(false);
        repository.saveAndFlush(entity);
    }

    @Given("title is {string}")
    public void titleIsTAJTL(String title) {
        entity.setTitle(title);
        repository.saveAndFlush(entity);
    }

    @When("Command {string} is sent")
    public void send_command(String command) {
        try {
            lastException = null;
            actions = mockMvc.perform(put("/tasks/" + entity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"cmd\": \"" + command + "\"}")
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception ex) {
            this.lastException = ex;
        }
    }

    @Then("Nothing happenes")
    public void nothing_happenes() throws Exception {
        actions.andExpect(status().isAccepted())
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.approved").isEmpty())
                .andExpect(jsonPath("$.status").value(status));
    }

    @Then("Command is accepted")
    public void command_is_accepted() throws Exception {
        actions.andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(entity.getId()));
    }

    @Then("Task is approved")
    public void is_approved() throws Exception {
        actions.andExpect(jsonPath("$.approved").value(true));
    }

    @Then("Task is rejected")
    public void is_rejected() throws Exception {
        actions.andExpect(jsonPath("$.approved").value(false));
    }

    @Then("Status is {string}")
    public void check_status(String status) throws Exception {
        actions.andExpect(jsonPath("$.status").value(status));
    }

    @When("Tile is changed to {string}")
    public void tileIsChangedToNEWTITLE(String title) {
        try {
            lastException = null;

            actions = mockMvc.perform(put("/tasks/" + entity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"cmd\": \"change-title\", \"title\":\"" + title + "\"}")
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception ex) {
            this.lastException = ex;
        }
    }

    @Then("{string} is thrown")
    public void commandIsNotAccepted(String exceptionStr) {
        assertEquals(exceptionStr, lastException.getCause().getClass().getSimpleName());
        assertNull(actions);
    }

    @And("Approved is {string}")
    public void approvedIsApproved(@Nullable String str) throws Exception {
        if (str != null && !str.trim().isBlank())
            if ("NULL".equals(str))
                actions.andExpect(jsonPath("$.approved").isEmpty());
            else
                actions.andExpect(jsonPath("$.approved").value(Boolean.valueOf(str)));

    }
}
