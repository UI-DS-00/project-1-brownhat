package com.example.imdbproject.security;


import com.example.imdbproject.filter.CostumeAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.http.HttpMethod.GET;


@Configuration //to be picked up by spring
@ComponentScan
@EnableWebSecurity
//@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
//this is where we are going to tell the application how we are going to manage the security and the users

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //WebSecurityConfigurerAdapter is the main security class

    //we need beans for each of these two
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        //this is the way we are about to find the users
        //we can also use -> inMemoryAuthentication , jdbcAuthentication
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        super.configure(auth);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //after giving the user a token , we are not going to track him down with cookies or etc , but we are going to use
        // jason token system instead of sessions

        //also we want to disable forgery in here:
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.authorizeRequests().antMatchers(GET,"users").hasAnyAuthority("ROLE_USER");
        //we want to give everyone access at this point
        http.authorizeRequests().antMatchers(GET, "/api/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new CostumeAuthenticationFilter(authenticationManagerBean()));

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{

        return super.authenticationManagerBean();
    }
}
