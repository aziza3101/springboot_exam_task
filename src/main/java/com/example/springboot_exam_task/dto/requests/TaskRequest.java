package com.example.springboot_exam_task.dto.requests;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter@Setter
public class TaskRequest {
    private String taskName;
    private String taskText;
    private LocalDate deadline;
    private Long lessonId;
}
