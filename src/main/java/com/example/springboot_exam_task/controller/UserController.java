package com.example.springboot_exam_task.controller;

import com.example.springboot_exam_task.dto.requests.UserRequest;
import com.example.springboot_exam_task.dto.responses.UserResponse;
import com.example.springboot_exam_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/user")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService service;
//
//    @PostMapping("/create")
//    public UserResponse create(@RequestBody UserRequest request) {
//        return service.craete(request);
//    }
//}

