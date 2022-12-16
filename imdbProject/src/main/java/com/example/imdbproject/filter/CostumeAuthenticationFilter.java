package com.example.imdbproject.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


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
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //after being successful , we need to send the info into the header ,etc

        //getting data to build web token
        User user = (User) authentication.getPrincipal();

        //giving string to algorithm to build token
        //giving the algorithm the way for generating refresh and sign in token
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        //set a string to generate token with unique parameters like using
        //the username of the user for making it unique
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                //set a time for expiration of access token and generating a new token afterward :)
                //bemanad be yadegar ke 100 bar avaz kardam in comment ro
                //the expiration time should be little because access token time is low
                //set 10 minutes for access token
                .withExpiresAt(new Date(System.currentTimeMillis()+ 100*60*1000))
                //using company name or issuer name for generation
                .withIssuer(request.getRequestURL().toString())
                //todo we dont know :/
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


        //generating refresh token
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())

                .withExpiresAt(new Date(System.currentTimeMillis()+ 200*60*1000))
                .withIssuer(request.getRequestURL().toString())
                //todo we dont know :/
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


        //these two lines return generated tokens
//        response.setHeader("access_token",accessToken);
//        response.setHeader("refresh_Token",refreshToken);


        //fellow lines create a set of tokens and then send them in json format
        Map<String,String> tokens = new HashMap<>();
        tokens.put("accessToken" , accessToken);
        tokens.put("refreshToken" , refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("failed");
        super.unsuccessfulAuthentication(request, response, failed);
        //DDOS :)
    }
}
