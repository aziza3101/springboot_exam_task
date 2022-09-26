package com.example.springboot_exam_task.controller;
import com.example.springboot_exam_task.dto.register.RegisterRequest;
import com.example.springboot_exam_task.dto.register.RegisterResponse;
import com.example.springboot_exam_task.dto.requests.LoginRequest;
import com.example.springboot_exam_task.dto.requests.UserRequest;
import com.example.springboot_exam_task.dto.responses.LoginResponse;
import com.example.springboot_exam_task.dto.responses.UserResponse;
import com.example.springboot_exam_task.entity.User;
import com.example.springboot_exam_task.repository.UserRepository;
import com.example.springboot_exam_task.security.jwt.JwtTokenUtil;
import com.example.springboot_exam_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {
    private final UserService userService;
    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginRequest login;
//    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request){
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
            User user = repository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(login.toLoginView(jwtTokenUtil.generatedToken(user),"Successfully",user));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(login.toLoginView("","login_failed",null));
        }
    }
    @PostMapping("/registration")
    public RegisterResponse create(@RequestBody RegisterRequest request){
        return userService.create(request);
    }
}







