package com.example.springboot_exam_task.controller;
import com.example.springboot_exam_task.dto.requests.StudentRequest;
import com.example.springboot_exam_task.dto.responses.SimpleResponse;
import com.example.springboot_exam_task.dto.responses.StudentResponse;
import com.example.springboot_exam_task.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PutMapping("/update/{id}")
    public StudentResponse updateStudentById(@PathVariable Long id,
                                             @RequestBody StudentRequest studentRequest) {
        return studentService.update(id, studentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteInstructorById(id);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> getAllStudents() {
        return studentService.findAll();
    }
}