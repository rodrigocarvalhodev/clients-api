package net.rodrigocarvalho.clientsapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {

    @NotEmpty(message = "{user.name.required}")
    private String username;

    @NotEmpty(message = "{user.password.required}")
    private String password;
}
