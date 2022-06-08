package net.rodrigocarvalho.clientsapi.exception;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException(Long id) {
        super("Usuário com o id " + id + " não encontrado");
    }
}
