package net.rodrigocarvalho.clientsapi.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    @NotEmpty(message = "{name.required}")
    private String name;

    @NotNull(message = "{cpf.required}")
    @CPF(message = "{cpf.invalid}")
    private String cpf;

    @NotNull
    private LocalDate registredTime;
}
