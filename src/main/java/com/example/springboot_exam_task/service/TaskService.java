package com.example.springboot_exam_task.service;
import com.example.springboot_exam_task.dto.requests.TaskRequest;
import com.example.springboot_exam_task.dto.responses.SimpleResponse;
import com.example.springboot_exam_task.dto.responses.TaskResponse;
import com.example.springboot_exam_task.entity.Lesson;
import com.example.springboot_exam_task.entity.Task;
import com.example.springboot_exam_task.exceptions.NotFoundException;
import com.example.springboot_exam_task.repository.LessonRepository;
import com.example.springboot_exam_task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    public TaskResponse saveTask(TaskRequest taskRequest) {
        Task task = mapToEntity(taskRequest);
        Lesson lesson = lessonRepository.findById(taskRequest.getLessonId())
                .orElseThrow(() -> new NotFoundException("lesson with id: " + taskRequest.getLessonId() + " does not exists"));
        lesson.addTasks(task);
        task.setLessons(lesson);
        taskRepository.save(task);
        return mapToView(task);
    }

    public TaskResponse findById(Long id) {
        Task task = getById(id);
        return mapToView(task);
    }

    public TaskResponse update(Long id, TaskRequest taskRequest) {
        Task task = getById(id);
        convertToUpdate(task, taskRequest);
        taskRepository.save(task);
        return mapToView(task);
    }
    public SimpleResponse deleteTaskById(Long id) {
        boolean exists = taskRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("task with id " + id + " not found!");
        }
        Task task = getById(id);
        taskRepository.delete(task);
        return new SimpleResponse(
                "DELETED",
                "task with id " + id + "deleted successfully"
        );
    }

    public List<TaskResponse> findAll() {
        return convertAllToView(taskRepository.findAll());
    }
    private Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("task with id: " + id + " not found!"));
    }
    public TaskResponse mapToView(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getTaskId());
        response.setTaskName(task.getTaskName());
        response.setTaskText(task.getTaskText());
        response.setDeadline(task.getDeadline());
        return response;
    }
    public Task mapToEntity(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        return task;
    }
    public Task convertToUpdate(Task task, TaskRequest taskRequest) {
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        return task;
    }
    public List<TaskResponse> convertAllToView(List<Task> tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(mapToView(task));
        }
        return taskResponses;
    }
}
