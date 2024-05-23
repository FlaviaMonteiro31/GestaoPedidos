package br.com.ms_pedidos.model.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemoverEstoqueRequest {
    private UUID idproduto;
    private BigDecimal quantidade;
}
