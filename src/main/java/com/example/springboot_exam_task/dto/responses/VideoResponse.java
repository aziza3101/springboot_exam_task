package com.example.springboot_exam_task.dto.responses;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter@Setter
public class VideoResponse {
    private Long id;
    private String videoName;
    private String link;
}
