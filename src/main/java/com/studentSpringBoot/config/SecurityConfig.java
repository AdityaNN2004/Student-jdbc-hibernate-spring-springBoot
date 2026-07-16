package com.studentSpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.studentSpringBoot.security.CustomAccessDeniedHandler;
import com.studentSpringBoot.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	return config.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exception -> exception
                    .accessDeniedHandler(accessDeniedHandler) 
                )
            .authorizeHttpRequests(auth -> auth
                // 1. PUBLIC ROUTES (Anyone can access)
                .requestMatchers("/auth/login", "/auth/dept/**").permitAll()
                
                // 2. ADMIN-ONLY ROUTES (Management, Provisioning, and Configurations)
                .requestMatchers("/dept/add-dept").hasRole("ADMIN")
                .requestMatchers("/auth/teacher/onboard").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/courses/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasRole("ADMIN")
                
                // 3. TEACHER-ONLY ROUTES (Strictly restricted from Admins and Students)
                .requestMatchers("/enrollments/*/final-grade").hasRole("TEACHER")
                .requestMatchers("/enrollments/*/status").hasAnyRole("TEACHER", "ADMIN") // Admin can change status (e.g., Suspend/Drop)
                
                // 4. STUDENT-ONLY ROUTES
                .requestMatchers("/enrollments/enroll").hasRole("STUDENT")
                
                // 5. SHARED / AUTHENTICATED ROUTES (User can read their own specific data)
                .requestMatchers(HttpMethod.GET, "/courses/**","/dept").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                .requestMatchers("/enrollments/student/*/report-card").hasAnyRole("STUDENT", "ADMIN")
                
                // 6. CATCH-ALL FALLBACK
                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
