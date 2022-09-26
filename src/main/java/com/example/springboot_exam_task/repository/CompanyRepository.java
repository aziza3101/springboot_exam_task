package com.example.springboot_exam_task.repository;
import com.example.springboot_exam_task.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company  c where upper(c.companyName)like concat('%',:text,'%') " +
            "or upper(c.locatedCountry)like concat('%',:text,'%') ")
    List<Company> searchCompanyByCompanyName(@Param("text") String text, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "delete c from Company c where c.companyId = :companyId" )
    void deleteCompany(Long companyId);
}