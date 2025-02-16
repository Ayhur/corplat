package com.inditex.core.platform.corplat.controller;

import com.inditex.core.platform.corplat.exception.NoContentException;
import io.swagger.v3.oas.annotations.Hidden;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@Hidden
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<Object> handleDateTimeParseException(NoContentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = {DateTimeParseException.class})
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date field is incorrect.");
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleDateTimeParseException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occured.");
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleDateTimeParseException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unnable service. Try later");
    }


}
