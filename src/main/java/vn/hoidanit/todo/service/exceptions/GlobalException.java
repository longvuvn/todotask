package vn.hoidanit.todo.service.exceptions;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import vn.hoidanit.todo.model.ApiResponse;

@ControllerAdvice
public class GlobalException {

    // HandleAllExceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleAllException(Exception ex) {
        ApiResponse<?> response = new ApiResponse<>(
                "error",
                "handleAllException",
                null,
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Handle NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(NoSuchElementException ex) {
        ApiResponse<?> response = new ApiResponse<>(
                "error",
                "handleNotFound",
                null,
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException ex) {
        ApiResponse<?> response = new ApiResponse<>(
                "error",
                "handleIllegalArgument",
                null,
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}