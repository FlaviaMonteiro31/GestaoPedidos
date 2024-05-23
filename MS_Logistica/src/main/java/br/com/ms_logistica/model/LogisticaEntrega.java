package br.com.ms_logistica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_logiticaEntrega")
public class LogisticaEntrega {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "entregaId", updatable = false, nullable = false)
    private UUID entregaId;
    private UUID pedido;
    private String cepEntrega;
    private String empresa;
    private String motorista;
    private Date dataCriacao;
    private int prazoDeEntrega;
    private Date dataEntrega;
    private String statusEntrega;

}
