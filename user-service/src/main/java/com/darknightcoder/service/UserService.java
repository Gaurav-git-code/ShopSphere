package com.darknightcoder.service;

import com.darknightcoder.dto.LoginRequestDto;
import com.darknightcoder.dto.LoginResponseDto;
import com.darknightcoder.dto.RegisterRequestDto;
import com.darknightcoder.dto.UserResponseDto;

public interface UserService {
    //sign Up
    UserResponseDto registerUser(RegisterRequestDto registerRequestDto);
    LoginResponseDto loginUser(LoginRequestDto loginRequestDto);


}
