package com.example.imdbproject.security;


import com.example.imdbproject.filter.CostumeAuthenticationFilter;
import com.example.imdbproject.filter.CustomAuthorizationFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


//@Configuration //to be picked up by spring
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


        CostumeAuthenticationFilter costumeAuthenticationFilter=new CostumeAuthenticationFilter(authenticationManagerBean());
        //changing the default login url
        costumeAuthenticationFilter.setFilterProcessesUrl("/api/login");



        //also we want to disable forgery in here:
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.authorizeRequests().antMatchers(GET,"users").hasAnyAuthority("ROLE_USER");
        //we want to give everyone access at this point



        //we should write down all of the URLs we want everyone to have access to , on top of the
        //commands in between '========' lines

        http.authorizeRequests().antMatchers("/api/login/**" , "/api/token/refresh", "/upload", "/swagger-ui/**").permitAll();
        http.authorizeRequests().antMatchers("/filter/**" , "/data/show/**", "/readFiles","/api/signup").permitAll();

      //the login path comes from : filter -> costumeAuthenticationFilter -> UsernamePasswordAuthenticationFilter.class

        //=======================================================================================

        //giving authorizations for each role
        http.authorizeRequests().antMatchers(GET, "/api/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/api/**").hasAnyAuthority("ROLE_ADMIN");


        //http.authorizeRequests().anyRequest().permitAll();
        //we want to authenticate e everyone which is why we are not going to use the command above
        http.authorizeRequests().anyRequest().authenticated();

        //=================================================================================

        http.addFilter(costumeAuthenticationFilter);

        //this filter should come before all the other filters because we should  intercept all the requests before any other filters
        //in the second parameter , we are telling what is this authorization for , which is for that ( UsernamePasswordAuthenticationFilter.class) specific class
        http.addFilterBefore(new CustomAuthorizationFilter() , UsernamePasswordAuthenticationFilter.class );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{

        return super.authenticationManagerBean();
    }
}
