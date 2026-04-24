package com.darknightcoder.service.impl;

import com.darknightcoder.constant.Role;
import com.darknightcoder.dto.LoginRequestDto;
import com.darknightcoder.dto.LoginResponseDto;
import com.darknightcoder.dto.RegisterRequestDto;
import com.darknightcoder.dto.UserResponseDto;
import com.darknightcoder.entity.ShopSphereUser;
import com.darknightcoder.mapper.UserMapper;
import com.darknightcoder.repository.ShopSphereUserRepository;
import com.darknightcoder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private ShopSphereUserRepository userRepository;
    private UserMapper mapper;
    private PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Override
    public UserResponseDto registerUser(RegisterRequestDto registerRequestDto) {
        boolean isEmailPresent = userRepository.findByEmail(registerRequestDto.getEmail()).isPresent();
        if (isEmailPresent) throw new RuntimeException(
                String.format("the email id %s is already used!",registerRequestDto.getEmail()));
        ShopSphereUser shopSphereUser = mapper.toEntity(registerRequestDto);
        shopSphereUser.setPassword(encoder.encode(registerRequestDto.getPassword()));
        shopSphereUser.setRole(Role.ROLE_USER);
        ShopSphereUser user = userRepository.save(shopSphereUser);
        return mapper.toDto(user);
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        ));
        ShopSphereUser user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();

        String token = jwtService.generateToken(user);
        return new LoginResponseDto(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                token,
                "Bearer"

        );

    }

}
