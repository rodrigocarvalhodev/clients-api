package net.rodrigocarvalho.clientsapi.model.response;

import lombok.Data;

@Data
public class AppUserResponse {

    private Long id;
    private String username;
    private String password;
}
