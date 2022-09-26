package com.example.springboot_exam_task.dto.responseView;
import com.example.springboot_exam_task.dto.responses.CompanyResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CompanyResponseView {
    private List<CompanyResponse> companyResponses;
    private int currentPage;
    private int totalPage;
}
