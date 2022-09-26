package com.example.springboot_exam_task.dto.responses;
import com.example.springboot_exam_task.entity.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Getter @Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
    private LocalDate created;
    private boolean enabled;
}
