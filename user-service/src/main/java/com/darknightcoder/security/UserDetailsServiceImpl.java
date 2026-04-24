package com.darknightcoder.security;

import com.darknightcoder.entity.ShopSphereUser;
import com.darknightcoder.repository.ShopSphereUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private ShopSphereUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopSphereUser user = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email id : %s is not found!",username)));
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole().name()));

        return new CustomUserDetails(authorityList,user.getPassword(),user.getEmail());
    }
}
