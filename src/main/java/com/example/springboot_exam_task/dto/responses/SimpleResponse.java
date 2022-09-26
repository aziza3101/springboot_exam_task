package com.example.springboot_exam_task.dto.responses;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class SimpleResponse {
    private String status;
    private String message;

    public SimpleResponse() {
    }

    public SimpleResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
