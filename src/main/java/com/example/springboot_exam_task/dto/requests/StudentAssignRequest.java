package com.example.springboot_exam_task.dto.requests;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StudentAssignRequest {
    private Long studentId;
    private Long courseId;
}
