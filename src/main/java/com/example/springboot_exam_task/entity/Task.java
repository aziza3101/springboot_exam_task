package com.example.springboot_exam_task.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;
@Entity
@Table(name = "tasks")
@Getter @Setter
@NoArgsConstructor
public class Task {
    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(generator = "task_seq", strategy = GenerationType.SEQUENCE)
    private Long taskId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_text")
    private String taskText;
    @Column(name = "dead_line")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;
    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH, DETACH})
    private Lesson lessons;

    public Task(String taskName, String taskText, LocalDate deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }
    public void addVideo(Video video) {
    }
}

