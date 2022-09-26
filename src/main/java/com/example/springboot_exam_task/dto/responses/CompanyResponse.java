package com.example.springboot_exam_task.dto.responses;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class CompanyResponse {
    private Long companyId;
    private String companyName;
    private String locatedCountry;
    private LocalDate createAt;
    private boolean isActive;
}
