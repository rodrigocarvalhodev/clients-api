package net.rodrigocarvalho.clientsapi.model.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientResponse {

    private Long id;
    private String name;
    private String cpf;
    private LocalDate registredTime;
}
