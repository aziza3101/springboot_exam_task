package com.example.springboot_exam_task.controller;
import com.example.springboot_exam_task.dto.requests.LessonRequest;
import com.example.springboot_exam_task.dto.responses.LessonResponse;
import com.example.springboot_exam_task.dto.responses.SimpleResponse;
import com.example.springboot_exam_task.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/lesson")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    @PostMapping("/save")
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(lessonRequest);
    }
    @GetMapping("/findById/{id}")
    public LessonResponse getLessonById(@PathVariable Long id) {
        return lessonService.findById(id);
    }
    @PutMapping("/update/{id}")
    public LessonResponse updateLessonById(@PathVariable Long id,
                                           @RequestBody LessonRequest lessonRequest) {
        return lessonService.update(id, lessonRequest);
    }
    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteLessonById(@PathVariable Long id) {
        return lessonService.deleteLessonById(id);
    }
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','STUDENT')")
    public List<LessonResponse> getAllLessons() {
        return lessonService.findAll();
    }
}
