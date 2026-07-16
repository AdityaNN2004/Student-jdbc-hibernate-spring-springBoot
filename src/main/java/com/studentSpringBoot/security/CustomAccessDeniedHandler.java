package com.studentSpringBoot.security;

import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentSpringBoot.exceptions.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        // 1. Set the response properties to JSON format with a 403 status code
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        // 2. Build our standard ErrorResponse object payload matching your production structure
        ErrorResponse errorDetails = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                "Access Denied: You do not possess the required security roles to execute this operation.",
                request.getRequestURI()
        );

        // 3. Serialize the Java object into raw JSON text output stream
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); // Ensures Java 8 LocalDateTime formats correctly
        response.getWriter().write(mapper.writeValueAsString(errorDetails));
    }
}
