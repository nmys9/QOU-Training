package com.qoutraining.employeedirectory.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");

        if(header!=null && header.startsWith("Bearer ")){
            String token=header.substring(7);
            String email= jwtUtil.extractEmail(token);

            UserDetails userDetails= customUserDetailsService.loadUserByUsername(email);

            String role= jwtUtil.extractRole(token);
            List<GrantedAuthority> authorities=List.of(new SimpleGrantedAuthority("ROLE_"+role));

            UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);
    }
}
