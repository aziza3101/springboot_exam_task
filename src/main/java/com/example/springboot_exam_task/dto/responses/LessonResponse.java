package com.example.springboot_exam_task.dto.responses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter@Setter
public class LessonResponse {
    private Long id;
    private String lessonName;
}
