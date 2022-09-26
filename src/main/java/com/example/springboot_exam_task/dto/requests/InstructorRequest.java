package com.example.springboot_exam_task.dto.requests;
import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
    private Long companyId;
    private String password;
}
