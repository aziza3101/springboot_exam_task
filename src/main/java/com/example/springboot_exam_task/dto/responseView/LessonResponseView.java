package com.example.springboot_exam_task.dto.responseView;
import com.example.springboot_exam_task.dto.responses.LessonResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class LessonResponseView {
    List<LessonResponse> lessonResponses;
}
