package com.example.springboot_exam_task.service;
import com.example.springboot_exam_task.dto.requests.CourseRequest;
import com.example.springboot_exam_task.dto.responseView.CourseResponseView;
import com.example.springboot_exam_task.dto.responses.CourseResponse;
import com.example.springboot_exam_task.entity.Company;
import com.example.springboot_exam_task.entity.Course;
import com.example.springboot_exam_task.exceptions.NotFoundException;
import com.example.springboot_exam_task.repository.CompanyRepository;
import com.example.springboot_exam_task.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourse_name(courseRequest.getCourse_name());
        course.setDescription(courseRequest.getDescription());
        course.setDuration(courseRequest.getDuration());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(courseRequest.getDateOfStart());
        Company company = companyRepository.findById(courseRequest.getCompanyId()).orElseThrow(
                () -> new NotFoundException(String.format("Company with =%s id not found",
                        courseRequest.getCompanyId())));
        course.setCompany(company);
        company.addCourse(course);
        Course course1 = courseRepository.save(course);
        return response(course1);
    }
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Course with =%s id not founded", id)));
        return response(course);
    }
    public Course updateCourse(Course course, CourseRequest courseRequest) {
        course.setCourse_name(courseRequest.getCourse_name());
        course.setDescription(courseRequest.getDescription());
        course.setDuration(courseRequest.getDuration());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(courseRequest.getDateOfStart());
        Company company = companyRepository.findById(courseRequest.getCompanyId()).orElseThrow(
                () -> new NotFoundException(String.format("Company with =%s id not found",
                        courseRequest.getCompanyId())));
        course.setCompany(company);
        company.addCourse(course);
        return courseRepository.save(course);
    }
    public CourseResponse updateCourseById(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Course with =%s id not found", id)));
        Course course1 = updateCourse(course, courseRequest);
        return response(course1);
    }
    public CourseResponse deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Course with =%s id not found", id)));
        course.setCompany(null);
        courseRepository.delete(course);
        return response(course);
    }
    public List<CourseResponse> getAllCourse() {
        return getAllCourses(courseRepository.findAll());
    }
    public List<CourseResponse> getAllCourses(List<Course> courses) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course : courses) {
            responses.add(response(course));
        }
        return responses;
    }
    public CourseResponseView getAllCoursesPagination(String text, int page, int size) {
        CourseResponseView courseResponseView = new CourseResponseView();
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("courseName"));
        courseResponseView.setCourseResponses(getAllCourses(search(text, pageable)));
        return courseResponseView;
    }
    private List<Course> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return courseRepository.searByCourseName(text.toUpperCase(Locale.ROOT), pageable);
    }
    public Long getCountOfCoursesByCompanyName(String companyName) {
        return courseRepository.countCoursesByCompanyName(companyName);
    }
    public CourseResponse response(Course course) {
        return new CourseResponse(course.getCourseId(),
                course.getCourse_name(),
                course.getDuration(),
                course.getDescription(),
                course.getImage(),course.getDateOfStart());
    }
}