package com.wearenotch.kluksa.commandstatedemo.web.rest;

import com.wearenotch.kluksa.commandstatedemo.domain.commands.AbstractTaskCommand;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskDetailsDto;
import com.wearenotch.kluksa.commandstatedemo.service.TaskService;
import com.wearenotch.kluksa.commandstatedemo.service.dto.TaskSubmitDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksResource {

    private final TaskService taskService;

    public TasksResource(@NotNull final TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public @NotNull ResponseEntity<TaskDetailsDto> getTask(@PathVariable(name = "id") final long id) {
        final Optional<TaskDetailsDto> taskDetails = this.taskService.getTaskDetails(id);
        return ResponseEntity.of(taskDetails);
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDetailsDto>> getAllTasks() {
        final List<TaskDetailsDto> page = this.taskService.getTasks();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @PostMapping
    public @NotNull ResponseEntity<TaskDetailsDto> submitTask(@RequestBody final TaskSubmitDto dto) {
        final TaskDetailsDto detailsDto = taskService.submit(dto);
        final URI location = URI.create("/tasks/" + detailsDto.getId());
        return ResponseEntity.created(location).body(detailsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDetailsDto> sendCommandToTask(@PathVariable("id") final long id,
                                               @RequestBody AbstractTaskCommand command) {

        return taskService.executeCommand(id, command)
            .map(dto -> ResponseEntity.accepted().body(dto))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
