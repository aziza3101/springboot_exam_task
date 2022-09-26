package com.example.springboot_exam_task.controller;
import com.example.springboot_exam_task.dto.requests.CourseRequest;
import com.example.springboot_exam_task.dto.responseView.CourseResponseView;
import com.example.springboot_exam_task.dto.responses.CourseResponse;
import com.example.springboot_exam_task.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")

    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourse();
    }

    @PostMapping("/saveCourse")
    public CourseResponse saveCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(courseRequest);
    }

    @GetMapping("/getCourseById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/update/{id}")
    public CourseResponse updateCourseById(@PathVariable Long id,
                                           @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourseById(id, courseRequest);
    }

    @DeleteMapping("/delete/{id}")
    public CourseResponse deleteCourseById(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("/coursePagination")
    public CourseResponseView getAllCoursesPagination(@RequestParam(name = "text", required = false)
                                                      String text,
                                                      @RequestParam int page,
                                                      @RequestParam int size) {
        return courseService.getAllCoursesPagination(text, page, size);
    }

    @GetMapping("/{companyName}/count")
    public Long getCountOfCoursesByCompanyName(@PathVariable String companyName) {
        return courseService.getCountOfCoursesByCompanyName(companyName);
    }
}
