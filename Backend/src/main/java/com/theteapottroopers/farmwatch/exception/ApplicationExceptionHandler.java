package com.theteapottroopers.farmwatch.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * this class handles exceptions in regard to the HTTP respone
 */

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = { LastAdminDeletionException.class })
    public ResponseEntity<Object> handleCustomException(LastAdminDeletionException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    public ResponseEntity<Object> handleCustomException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(value = { ExpiredJwtException.class })
    public ResponseEntity<Object> handleCustomException(ExpiredJwtException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Please login again\n\n" + exception.getMessage());
    }

    @ExceptionHandler(value = { LoginException.class })
    public ResponseEntity<Object> handleCustomException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Please login again");
    }




}
