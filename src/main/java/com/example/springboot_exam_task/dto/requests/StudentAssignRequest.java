package com.example.springboot_exam_task.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAssignRequest {
    private String studentId;
    private String LessonId;
}
