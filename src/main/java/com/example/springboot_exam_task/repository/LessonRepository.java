package com.example.springboot_exam_task.repository;
import com.example.springboot_exam_task.dto.requests.VideoRequest;
import com.example.springboot_exam_task.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
