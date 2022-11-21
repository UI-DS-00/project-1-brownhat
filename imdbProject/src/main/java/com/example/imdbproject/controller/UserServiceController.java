package com.example.imdbproject.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Role;
import com.example.imdbproject.model.request.RoleToUserForm;
import com.example.imdbproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController

@AllArgsConstructor
@RequestMapping ("/api")

public class UserServiceController {


    private final UserService userService;
    private final UserService userServiceImp;

    @PostMapping("/user/rating")
    public void makeRating(@RequestBody String titleBasic, @RequestBody Float ratingAmount) {
        userService.rating(titleBasic, ratingAmount);
    }

    @PostMapping("/user/make/watchlist/{userId}")
    public void makeWatchList(@RequestBody String name, @PathVariable Integer userId) {
        userService.makeWatchList(name, userId);
    }

    @PostMapping("/user/add/to/watchlist/{id}")
    public void addToWatchList(@RequestBody String filmId, @RequestBody String watchlistName, @PathVariable Integer id) {
        userServiceImp.addFilmToWatchList(id, watchlistName, filmId);
    }


    @PostMapping("/user/add/new/comment/{titleBasic}/{id}")
    public void addComment(@RequestBody String commentText, @PathVariable String titleBasic, @PathVariable Integer id) {

        userServiceImp.addComment(id, commentText, titleBasic);

    }


    //--------------------------------------------------------------------------JWT :)

    @GetMapping("/admin/users")
    public ResponseEntity<List<AllUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUser());
        //return new ResponseEntity<>(userServiceImp.getUser(), HttpStatus.OK);
    }

    @PostMapping("/admin/saveuser")
    public ResponseEntity<AllUser> saveUser(@RequestBody AllUser user) {

        //creating uri to send data to the url and change it to a string
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/loggedIn/user/save")
                .toUriString());

        //this uri is going to be added to the header of the new user header
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }


    @PostMapping("/admin/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/loggedIn/role/save")
                .toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


    @PostMapping("/admin/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {

        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        //we wrote it down in two lines , using build instead of body , because
        // the addRoleToUser function output is void
        return ResponseEntity.ok().build();
    }


    //make a request for the user so they can renew their token after the refresh token was expired
    //so we have to send the refresh token so it can makes us a new access token

    @PostMapping("/token/refresh")
    //injecting request and response so we can have access to it
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //by getting the header , we are going to check the authorization
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        //if the authorization header is there and it has bearer , this is the place we are going to put our logic in
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            //using bearer to confirm a token is valid and doesn't need any further checking for validation

            try {
                //removing Bearer from the refresh_token
                String refresh_token = authorizationHeader.substring("Bearer ".length());


                // defining the same algorithm from customAuthentication
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                //creating the verifier with same secrete and algorithm to encode it
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();


                //decoding the refresh_token and verifying it
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);


                //getting the username that comes with the refresh_token
                String username = decodedJWT.getSubject();

                //once we get the username ,we need to load him , so we are going to find the user inside the database
                Optional<AllUser> user = userService.getUser(username);



                //creating a new access refresh_token
                String accessToken = JWT.create()
                        .withSubject(user.get().getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
                        //using company name or issuer name for generation
                        .withIssuer(request.getRequestURL().toString())
                        //todo we dont know :/
                        .withClaim("roles", user.get().getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);


                //fellow lines create a set of tokens and then send them in json format
                Map<String,String> tokens = new HashMap<>();
                tokens.put("accessToken" , accessToken);
                tokens.put("refreshToken" , refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //it is going to give us th forbidden code

                // response.sendError(FORBIDDEN.value());
                //these lines are going to do the comment above
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
            //removing bearer from token

        } else {
            //that means we don't have any refresh token
            throw new RuntimeException("refresh token is missing");
        }
    }


}