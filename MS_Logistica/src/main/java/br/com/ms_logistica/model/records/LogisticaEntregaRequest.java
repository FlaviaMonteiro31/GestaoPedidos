package br.com.ms_logistica.model.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticaEntregaRequest {
    private UUID pedido;
    private String cepEntrega;
}
