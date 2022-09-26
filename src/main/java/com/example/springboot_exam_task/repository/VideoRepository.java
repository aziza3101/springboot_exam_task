package com.example.springboot_exam_task.repository;
import com.example.springboot_exam_task.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}