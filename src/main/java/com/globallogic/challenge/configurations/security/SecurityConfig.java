package com.globallogic.challenge.configurations.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    final var jwtTokenProvider = new JwtTokenProvider();
    final var jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);

    http.csrf(csrf -> csrf.disable())
        .authorizeRequests(requests -> requests
            .antMatchers("/api/sign-up").permitAll()
            // .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}