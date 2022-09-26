package com.example.springboot_exam_task.repository;
import com.example.springboot_exam_task.dto.responses.CourseResponse;
import com.example.springboot_exam_task.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select new com.example.springboot_exam_task.dto.responses.CourseResponse(c.courseId," +
            "c.course_name,c.duration,c.image,c.description,c.dateOfStart) from Course c")
    List<CourseResponse> getAllCourses();

    @Query("select c from Course c where upper(c.course_name) like upper(concat('%',:text, '%')) ")
    List<Course> searByCourseName(@Param("text") String text, Pageable pageable);


    @Query("select count(c) from Course c where c.company.companyName =?1")
    Long countCoursesByCompanyName(@Param("companyName") String companyName);

}