package net.rodrigocarvalho.clientsapi.persistance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceProvided {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @Column
    private BigDecimal value;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate providedTime;

}
