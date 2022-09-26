package com.example.springboot_exam_task.service;
import com.example.springboot_exam_task.dto.requests.InstructorAssignRequest;
import com.example.springboot_exam_task.dto.requests.InstructorRequest;
import com.example.springboot_exam_task.dto.responses.InstructorResponse;
import com.example.springboot_exam_task.dto.responses.SimpleResponse;
import com.example.springboot_exam_task.entity.*;
import com.example.springboot_exam_task.exceptions.NotFoundException;
import com.example.springboot_exam_task.repository.CompanyRepository;
import com.example.springboot_exam_task.repository.CourseRepository;
import com.example.springboot_exam_task.repository.InstructorRepository;
import com.example.springboot_exam_task.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = mapToEntity(instructorRequest);
        Company company = companyRepository.findById(instructorRequest.getCompanyId())
                .orElseThrow(() -> new NotFoundException("company with id: " + instructorRequest.getCompanyId() + " does not exists"));
        company.addInstructor(instructor);
        instructor.setCompany(company);
        User user = new User();
        user.setFirstName(instructorRequest.getFirstName());
        user.setEmail(instructorRequest.getEmail());
        user.setPassword(passwordEncoder.encode(instructorRequest.getPassword()));
        List<Role> roles = roleRepository.findRoleByRoleName("INSTRUCTOR");
        user.setRoles(roles);
        instructor.setUser(user);
        instructorRepository.save(instructor);
        return mapToView(instructor);
    }

    public InstructorResponse findById(Long id) {
        Instructor instructor = getById(id);
        return mapToView(instructor);
    }
    private Instructor getById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("instructor with id: " + id + " not found !"));
    }

    public InstructorResponse update(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = getById(id);
        convertToUpdate(instructor, instructorRequest);
        instructorRepository.save(instructor);
        return mapToView(instructor);
    }
    public SimpleResponse deleteInstructorById(Long id) {
        boolean exists = instructorRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("instructor with id " + id + " not found!");
        }
        instructorRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "instructor with id " + id +  "deleted successfully"
        );
    }

    public List<InstructorResponse> findAll() {
        return convertAllToView(instructorRepository.findAll());
    }

    public SimpleResponse assignInstructorToCourse(InstructorAssignRequest instructorAssignRequest) {
        Instructor instructor = getById(instructorAssignRequest.getInstructorId());
        Course course = courseRepository.findById(instructorAssignRequest.getCourseId()).orElseThrow(() -> new NotFoundException("course with id: " + instructorAssignRequest.getCourseId() + "not found"));
        instructor.addCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        return new SimpleResponse(
                "ASSIGNED",
                "assigned successfully"
        );
    }
    public String assign(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NotFoundException(String.format("Instructor with =%s id not found", instructorId)));
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with =%s id not found", instructorId)));
        instructor.addCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        return String.format("Instructor with =%s id assigned to course", instructorId);
    }
    public InstructorResponse mapToView(Instructor instructor) {
        InstructorResponse response = new InstructorResponse();
        response.setId(instructor.getInstructorId());
        response.setFirstName(instructor.getFirstName());
        response.setLastName(instructor.getLastName());
        response.setPhoneNumber(instructor.getPhoneNumber());
        response.setEmail(instructor.getEmail());
        response.setSpecialization(instructor.getSpecialization());
        return response;
    }
    public Instructor mapToEntity(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(instructorRequest.getCompanyId());
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }
    public Instructor convertToUpdate(Instructor instructor, InstructorRequest instructorRequest) {
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }
    public List<InstructorResponse> convertAllToView(List<Instructor> instructors) {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorResponses.add(mapToView(instructor));
        }
        return instructorResponses;
    }

}