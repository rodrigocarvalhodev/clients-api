package net.rodrigocarvalho.clientsapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rodrigocarvalho.clientsapi.persistance.entity.Client;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProvidedRequest {

    @NotEmpty(message = "{service.description.required}")
    private String description;

    @NotNull(message = "{service.client.required}")
    private Long clientId;

    @NotEmpty(message = "{service.price.required}")
    private String value;

    @NotEmpty(message = "{service.date.required}")
    private String providedTime;
}
