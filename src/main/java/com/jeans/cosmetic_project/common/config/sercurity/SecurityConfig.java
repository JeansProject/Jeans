package com.jeans.cosmetic_project.common.config.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PERMIT_ALL_PATTERNS = new String[] {
        "/",
        "/login/**",
        "/register/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

//        http
//            .authorizeHttpRequests(request ->
//                request
//                    .requestMatchers(
//                        Stream
//                            .of(PERMIT_ALL_PATTERNS)
//                            .map(AntPathRequestMatcher::new)
//                            .toArray(AntPathRequestMatcher[]::new)
//                    )
//                    .permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/my-page")).hasRole("USER")
//                    .anyRequest().authenticated()
//            );
        http
            .authorizeHttpRequests()
            .antMatchers("/")
            .permitAll()
            .antMatchers("/main")
            .permitAll()
            .antMatchers("/register")
            .permitAll()
            .antMatchers("/login/**")
            .permitAll()
            .and()
            .formLogin().disable()
            .csrf().disable();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.*");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
