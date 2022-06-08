package net.rodrigocarvalho.clientsapi.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ErrorsException {

    @Getter
    private List<String> errors;

    public ErrorsException(List<String> errors) {
        this.errors = errors;
    }

    public ErrorsException(String error) {
        this.errors = Arrays.asList(error);
    }


}
