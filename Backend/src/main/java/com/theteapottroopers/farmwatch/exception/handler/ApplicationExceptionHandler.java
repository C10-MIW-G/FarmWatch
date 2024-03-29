package com.theteapottroopers.farmwatch.exception.handler;

import com.theteapottroopers.farmwatch.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.DateTimeException;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * this class handles exceptions in regard to the HTTP response
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

    @ExceptionHandler(value = { PropertyValueException.class })
    public ResponseEntity<Object> handleCustomException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("All the required fields should be filled in");
    }

    @ExceptionHandler(value = { FieldHasNoInputException.class })
    public ResponseEntity<Object> handleCustomException(FieldHasNoInputException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = { InputHasDuplicateException.class })
    public ResponseEntity<Object> handleCustomException(InputHasDuplicateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = { AnimalNotFoundException.class })
    public ResponseEntity<Object> handleCustomException(AnimalNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = { InputIsToLargeException.class })
    public ResponseEntity<Object> handleCustomException(InputIsToLargeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = { DateTimeException.class })
    public ResponseEntity<Object> handleCustomException(DateTimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = { SomethingWentWrongException.class })
    public ResponseEntity<Object> handleCustomException(SomethingWentWrongException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
