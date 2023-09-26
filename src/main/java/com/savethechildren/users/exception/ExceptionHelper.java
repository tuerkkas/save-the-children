package com.savethechildren.users.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class ExceptionHelper {

    /**
     * Exception used to create a response with failure Record creation due to Record Exist on database.
     * @param recordExistsException
     * @return
     */
    @ExceptionHandler(value = {RecordExistsException.class})
    public ResponseEntity<Object> handleRecordExist(RecordExistsException recordExistsException) {
        log.error(recordExistsException.getMessage());
        return new ResponseEntity(recordExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<Object> handleException(RecordNotFoundException recordNotFoundException) {
        log.error(recordNotFoundException.getMessage());
        return new ResponseEntity(recordNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {org.springframework.web.bind.MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity(ex.getBindingResult().getFieldErrors().stream().map(fieldError -> new StringBuilder(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).toString()).collect(Collectors.toList()) , HttpStatus.BAD_REQUEST);
    }

}
