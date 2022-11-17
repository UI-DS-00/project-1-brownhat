package com.example.imdbproject.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Slf4j
public class CostumeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    //for authenticating the user , we need AuthenticationManager to be called
    private final AuthenticationManager authenticationManager;

    //constructor
    public CostumeAuthenticationFilter(AuthenticationManager authenticationManager){
        //we tell authenticationManager to authenticate the user
        this.authenticationManager = authenticationManager;
    }

    //In the fellow function we grab username and password from the request and pass it to UsernamePasswordAuthenticationToken

    //If login was successful, it calls successfulAuthentication
    //else it calls unsuccessfulAuthentication
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //This is the way we reach username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("username id: {}" , username);
        log.info("password id: {}" , password);
        //This gets username and password to create token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        //we tell authenticationManager to authenticate the user
        return authenticationManager.authenticate(authenticationToken);


    }


    //this is for the time when the login was successful
    // so in this case we are going give the user the access
    //and refresh token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //after being successful , we need to send the info into the header ,etc

        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        //DDOS :)
    }
}
