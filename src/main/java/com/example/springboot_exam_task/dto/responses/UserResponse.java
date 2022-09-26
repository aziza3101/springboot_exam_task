package com.example.springboot_exam_task.dto.responses;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created;
    private boolean isActive;
}

