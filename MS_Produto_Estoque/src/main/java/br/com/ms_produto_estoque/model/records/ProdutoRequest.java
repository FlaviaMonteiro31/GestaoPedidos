package br.com.ms_produto_estoque.model.records;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoRequest {
    private UUID id;
    private BigDecimal quantidade;
}
