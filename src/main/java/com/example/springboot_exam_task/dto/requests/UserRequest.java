package com.example.springboot_exam_task.dto.requests;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class UserRequest {
    private String name;
    private String password;
    private String email;
}
