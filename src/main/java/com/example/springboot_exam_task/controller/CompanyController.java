package com.example.springboot_exam_task.controller;
import com.example.springboot_exam_task.dto.requests.CompanyRequest;
import com.example.springboot_exam_task.dto.responseView.CompanyResponseView;
import com.example.springboot_exam_task.dto.responses.CompanyResponse;
import com.example.springboot_exam_task.dto.responses.SimpleResponse;
import com.example.springboot_exam_task.entity.Company;
import com.example.springboot_exam_task.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Company API", description = "User with role admin can add, update, delete or get all companies")
public class CompanyController {
    private final CompanyService service;

    @PostMapping("/save")
    public CompanyResponse create(@RequestBody CompanyRequest company) {
        return service.addCompany(company);
    }

    @GetMapping("/get/{id}")
    public CompanyResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{companyId}")
    public SimpleResponse deleteById(@PathVariable Long companyId) {
        return service.deleteById(companyId);
    }

    @PutMapping("/block/{id}")
    public CompanyResponse blockStudent(@PathVariable Long id) {
        return service.block(id);

    }

    @PutMapping("/update/{id}")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request) {
        return service.updateCompany(id, request);
    }

    @GetMapping("/getAll")
    public List<CompanyResponse> getAll() {
        return service.findAllCompany();
    }

    public CompanyResponseView getAllCompaniesPagination(@RequestParam(name = "text", required = false) String text,
                                                @RequestParam int page,
                                                @RequestParam int size) {
        return service.getAllCompaniesPagination(text, page, size);
    }
}