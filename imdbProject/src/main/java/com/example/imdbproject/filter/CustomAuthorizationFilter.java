package com.example.imdbproject.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    // all the logic we want to put , for the user for the permission of accessing specific places
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //if this was the case , we don't really want to do anything and let the user pass
        if (request.getServletPath().equals("/api/login")){
            //we don't really do anything here
            filterChain.doFilter(request , response);
        }//this is where we are about to check the user authorization
        else {

            //first we are going to get access to the authorization header ,the one, which is the key
            //for that token and this is where we are passing the key(AUTHORIZATION) we are looking for
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            //if the authorization header is there and it has bearer , this is the place we are going to put our logic in
            if (authorizationHeader != null  && authorizationHeader.startsWith("Bearer ")){
                //using bearer to confirm a token is valid and doesn't need any further checking for validation

                try {
                    String token = authorizationHeader.substring("Bearer ".length());


                    // defining the same algorithm from customAuthentication
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                    //creating the verifier with same secrete and algorithm
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();


                    //decoding the token and verifying it
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);


                    // when we find out that username is valid we get the data
                    String username = decodedJWT.getSubject();

                    //getting roles
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                    //passing collection
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                }catch (Exception exception){

                }
                //removing bearer from token

            }
        }
    }
}
