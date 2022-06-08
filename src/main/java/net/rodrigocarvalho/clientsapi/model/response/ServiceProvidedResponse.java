package net.rodrigocarvalho.clientsapi.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServiceProvidedResponse {

    private Long id;
    private String description;
    private ClientResponse client;
    private BigDecimal value;
    private LocalDate providedTime;
}
