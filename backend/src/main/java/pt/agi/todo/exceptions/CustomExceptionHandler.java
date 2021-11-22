package pt.agi.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger myLogger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(ToDoNotFoundException.class)
    public ResponseEntity<Object> handleToDoNotFound(ToDoNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidToDoContentException.class)
    public ResponseEntity<Object> handleToDoNotInvalidContent(InvalidToDoContentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}