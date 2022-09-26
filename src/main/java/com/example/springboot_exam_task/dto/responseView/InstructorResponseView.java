package com.example.springboot_exam_task.dto.responseView;
import com.example.springboot_exam_task.dto.responses.InstructorResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class InstructorResponseView {
    List<InstructorResponse> instructorResponses;
}
