package com.example.springboot_exam_task.controller;
import com.example.springboot_exam_task.dto.requests.VideoRequest;
import com.example.springboot_exam_task.dto.responses.SimpleResponse;
import com.example.springboot_exam_task.dto.responses.VideoResponse;
import com.example.springboot_exam_task.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/video")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    @PostMapping("/save")
    public VideoResponse saveVideo(@RequestBody VideoRequest videoRequest) {
        return videoService.saveVideo(videoRequest);
    }
    @GetMapping("/findById/{id}")
    public VideoResponse getVideoById(@PathVariable Long id) {
        return videoService.findById(id);
    }
    @PutMapping("/update/{id}")
    public VideoResponse updateVideoById(@PathVariable Long id,
                                         @RequestBody VideoRequest videoRequest) {
        return videoService.update(id, videoRequest);
    }
    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteVideoById(@PathVariable Long id) {
        return videoService.deleteVideoId(id);
    }
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    public List<VideoResponse> getAllVideos() {
        return videoService.findAll();
    }
}
