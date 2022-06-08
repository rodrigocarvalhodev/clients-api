package net.rodrigocarvalho.clientsapi.exception;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String login) {
        super("Usuário já cadastrado para o login " + login);
    }
}
