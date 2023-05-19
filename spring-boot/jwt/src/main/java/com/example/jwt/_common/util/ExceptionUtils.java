package com.example.jwt._common.util;

import com.example.jwt._common.application.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void handleException(
            HttpServletResponse response,
            HttpStatus status
    ) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(objectMapper.writeValueAsString(ApiResponse.error(status.name())));
            writer.flush();
        }
    }
}
