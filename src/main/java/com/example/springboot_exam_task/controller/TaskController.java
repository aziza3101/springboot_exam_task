package com.example.springboot_exam_task.controller;

import com.example.springboot_exam_task.dto.requests.TaskRequest;
import com.example.springboot_exam_task.dto.responses.SimpleResponse;
import com.example.springboot_exam_task.dto.responses.TaskResponse;
import com.example.springboot_exam_task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/task")
@PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'STUDENT')")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/save")
    public TaskResponse saveTask(@RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(taskRequest);
    }

    @GetMapping("/findById/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PutMapping("/update/{id}")
    public TaskResponse updateTaskById(@PathVariable Long id,
                                       @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<TaskResponse> getAllTasks() {
        return taskService.findAll();
    }
}
