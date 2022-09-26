package com.example.springboot_exam_task.repository;
import com.example.springboot_exam_task.entity.Company;
import com.example.springboot_exam_task.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student  s where upper(s.firstName)like concat('%',:text,'%') " +
            "or upper(s.lastName)like concat('%',:text,'%') " + "or upper(s.phoneNumber) like concat('%', :text, '%') " + "or upper(s.email) like concat('%', :text, '%') ")
    List<Company> searchCompanyByCompanyName(@Param("text") String text, Pageable pageable);
}