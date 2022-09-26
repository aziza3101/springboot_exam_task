package com.example.springboot_exam_task.dto.responses;
import com.example.springboot_exam_task.entity.Company;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
    private Company companyId;

}
