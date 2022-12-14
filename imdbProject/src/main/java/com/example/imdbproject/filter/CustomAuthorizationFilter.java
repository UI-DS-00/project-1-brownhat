package com.example.imdbproject.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    // all the logic we want to put , for the user for the permission of accessing specific places
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //if these were the paths we are in, we don't really want to do anything and let the user pass
        if (request.getServletPath().equals("/api/login")  || request.getServletPath().equals("/api/token/refresh")){
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
                    //removing Bearer from the token
                    String token = authorizationHeader.substring("Bearer ".length());


                    // defining the same algorithm from customAuthentication
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                    //creating the verifier with same secrete and algorithm to encode it
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();


                    //decoding the token and verifying it
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);


                    //getting the username that comes with the token
                    String username = decodedJWT.getSubject();

                    //getting roles
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                    //passing collection
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    //we should convert all roles to something that extends simpleGrantedAuthority
                    stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                    //we pass password null because we don't have it and nor need it
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username , null ,authorities);


                    //this is where we are talking about our user and giving all of his info and his permissions with jwt
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    //let the user keep going and continue
                    filterChain.doFilter(request , response);

                }catch (Exception exception){
                    log.error("error logging in {}" , exception.getMessage());
                    response.setHeader("error" , exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    //it is going to give us th forbidden code

                   // response.sendError(FORBIDDEN.value());
                    //these lines are going to do the comment above
                    Map<String,String> error = new HashMap<>();
                    error.put("error_message" , exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
                //removing bearer from token

            }else {
                //let the request continue
                filterChain.doFilter(request , response);
            }
        }
    }
}
