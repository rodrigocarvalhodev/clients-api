package net.rodrigocarvalho.clientsapi.controller;

import net.rodrigocarvalho.clientsapi.exception.ErrorsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsException handleValidationErrors(MethodArgumentNotValidException exception) {
        var collect = exception.getBindingResult().getAllErrors().
                stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ErrorsException(collect);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorsException> errorResponseStatusException(ResponseStatusException exception) {
        var message = exception.getReason();
        var status = exception.getStatus();
        var errorsException = new ErrorsException(message);
        return new ResponseEntity<>(errorsException, status);
    }
}
