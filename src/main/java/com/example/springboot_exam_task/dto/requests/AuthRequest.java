package com.example.springboot_exam_task.dto.requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter@Setter
@AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}
