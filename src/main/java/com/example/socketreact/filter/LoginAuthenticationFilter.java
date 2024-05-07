package com.example.socketreact.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public LoginAuthenticationFilter(final String defaultFilterPRocessUrl,
                                     final AuthenticationManager authenticationManager){
        super(defaultFilterPRocessUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException{
        String method = request.getMethod();
        if(!method.equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supoorted");
        }

        ServletInputStream inputStream = request.getInputStream();

        LoginRequestDto loginRequestDto = new ObjectMapper().readValue(inputStream, LoginRequestDto.class);

        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.username,
                loginRequestDto.password
        ));


    }

    public record LoginRequestDto(String username, String password){

    }

}
