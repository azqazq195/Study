package com.example.jwt._common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/health").permitAll()
                                .requestMatchers("/auth/sign-in").permitAll()
                                .requestMatchers("/auth/sign-up").permitAll()
                                .requestMatchers("/auth/refresh").permitAll()
                                .requestMatchers("/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest().authenticated()
                )
//                .exceptionHandling(exceptionHandling ->
//                                exceptionHandling.authenticationEntryPoint(authenticationEntryPointImpl)
//                        accessDeniedHandler(accessDeniedHandlerImpl)
//                )
//                .addFilterBefore(
//                        JwtAuthenticationFilter(tokenProvider),
//                        UsernamePasswordAuthenticationFilter::class.java
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
            web.ignoring().requestMatchers(new AntPathRequestMatcher("/docs/**"));
        };
    }
}
