package com.darknightcoder.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    private static final List<String> WHITE_LIST = List.of(
            "/api/v1/users/registry",
            "/api/v1/users/login"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (WHITE_LIST.stream().anyMatch(path::startsWith)){
            filterChain.doFilter(request,response);
            return;
        }
        String authHeader = request.getHeader("Authorization");

        if (authHeader== null || !authHeader.startsWith("Bearer ")){
            sendError(response,"Missing or invalid Authorization header");
            return;
        }
        String token = authHeader.substring(7);
        try{
            Claims claim =Jwts.parser()
                            .verifyWith(key)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
            String userId = claim.getSubject();
            String role = claim.get("role", String.class);

            log.info("Authenticated userId: {}, role: {}", userId, role);
            HeaderMutatingRequest mutatingRequest = new HeaderMutatingRequest(
                    request, userId, role
            );
            filterChain.doFilter(mutatingRequest, response);

        }catch (ExpiredJwtException ex){
            sendError(response,"Token was expired.");

        }catch (JwtException ex){
            sendError(response,"Invalid token");
        }
    }
    private void sendError(HttpServletResponse response,
                           String message) throws IOException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(
                "{\"error\": \"" + message + "\"}"
        );
    }
}
