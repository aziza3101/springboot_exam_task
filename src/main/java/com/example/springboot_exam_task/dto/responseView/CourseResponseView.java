package com.example.springboot_exam_task.dto.responseView;

import com.example.springboot_exam_task.dto.responses.CourseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseView {
    List<CourseResponse> courseResponses;
}
