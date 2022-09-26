package com.example.springboot_exam_task.dto.responses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.management.relation.Role;
@Getter @Setter
@AllArgsConstructor
public class AuthorResponse {
    private String email;
    private String token;
    private Role role;
}
