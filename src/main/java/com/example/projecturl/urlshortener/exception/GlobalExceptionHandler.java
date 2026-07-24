package com.example.projecturl.urlshortener.exception;

import com.example.projecturl.urlshortener.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(
            ResponseStatusException ex,
            HttpServletRequest req
            )
    {
        ErrorResponse er = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getReason(),
                req.getRequestURI()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(er);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,HttpServletRequest req
    )
    {
            String message = ex.getBindingResult()
                    .getFieldError()
                    .getDefaultMessage();
            ErrorResponse er = new ErrorResponse(
                    LocalDateTime.now(),
                    ex.getStatusCode().value(),
                    message,
                    req.getRequestURI()
            );
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(er);
    }

}
