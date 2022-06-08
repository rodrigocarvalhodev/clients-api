package net.rodrigocarvalho.clientsapi.persistance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=150)
    @NotEmpty (message = "{name.required}")
    private String name;

    @Column(nullable = false, length=11)
    @NotNull (message = "{cpf.required}")
    @CPF (message = "{cpf.invalid}")
    private String cpf;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registredTime;

}
