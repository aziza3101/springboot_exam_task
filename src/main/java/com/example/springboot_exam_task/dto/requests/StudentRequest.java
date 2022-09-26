package com.example.springboot_exam_task.dto.requests;
import com.example.springboot_exam_task.entity.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
    private Long companyId;
    private Long courseId;
    private String password;
}
