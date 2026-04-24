package com.darknightcoder.controller;

import com.darknightcoder.dto.LoginRequestDto;
import com.darknightcoder.dto.LoginResponseDto;
import com.darknightcoder.dto.RegisterRequestDto;
import com.darknightcoder.dto.UserResponseDto;
import com.darknightcoder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private UserService userService;


    @PostMapping(value = "/registry")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody RegisterRequestDto requestDto){
        return new ResponseEntity<>(
               userService.registerUser(requestDto), HttpStatus.CREATED
        );
    }



    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(userService.loginUser(loginRequestDto), HttpStatus.OK);
    }
}
