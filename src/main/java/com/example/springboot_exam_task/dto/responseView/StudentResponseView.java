package com.example.springboot_exam_task.dto.responseView;
import com.example.springboot_exam_task.dto.responses.StudentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StudentResponseView {
    List<StudentResponse> studentResponses;
    private int currentPage;
    private int totalPage;
}
