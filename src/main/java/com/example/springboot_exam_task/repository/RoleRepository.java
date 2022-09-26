package com.example.springboot_exam_task.repository;

import com.example.springboot_exam_task.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRoleByRoleName(String roleName);
}