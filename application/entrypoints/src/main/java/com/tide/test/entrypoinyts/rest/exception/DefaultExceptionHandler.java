package com.tide.test.entrypoinyts.rest.exception;

import com.tide.test.core.usercase.exceptions.AccountNotFoundException;
import com.tide.test.core.usercase.exceptions.InsufficientBalanceException;
import com.tide.test.core.usercase.exceptions.InvalidAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> accountNotFound(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Object> insufficientBalance(){
        // this can be improved by having a proper error json being created
        return new ResponseEntity<Object>("INSUFFICIENT_BALANCE",HttpStatus.OK);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> anyRuntimeException(){
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Object> invalidAmount(){
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }
}
