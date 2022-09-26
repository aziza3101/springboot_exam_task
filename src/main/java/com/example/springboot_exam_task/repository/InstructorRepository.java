package com.example.springboot_exam_task.repository;
import com.example.springboot_exam_task.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select count(i) from Instructor i where i.firstName =?1")
    Long getCountOfInstructors(@Param("firstName") String firstName);


    @Query("select count (i) from Instructor i where i.company.companyName =?1")
    Long countInstructorsByCompanyName(@Param("companyName") String companyName);


}