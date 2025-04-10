package org.example.springbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/register","/login", "/js/**", "/css/**")
                    .permitAll()
            //.antMatchers("/admin").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
            .and()
            .exceptionHandling()
                .accessDeniedPage("/unauthorized")
            .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error")
                .usernameParameter("j_login")
                .passwordParameter("j_password")
                .permitAll()
            .and()
            .oauth2Login()
                .loginPage("/login.html")
                .successHandler(authenticationSuccessHandler)
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        return http.build();
    }
}
