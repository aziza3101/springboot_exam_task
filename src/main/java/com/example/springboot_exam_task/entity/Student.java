package com.example.springboot_exam_task.entity;
import com.example.springboot_exam_task.entity.enums.StudyFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "students")
@Getter @Setter
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private Long studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column(name = "study_format")
    @Enumerated(value = EnumType.STRING)
    public StudyFormat studyFormat;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH, PERSIST})
    public Company company;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH, DETACH})
    private Course course;

    @OneToOne(cascade = ALL)
    private User user;


    public Student(String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}

