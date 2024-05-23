package br.com.ms_pedidos.model.records;

import br.com.ms_pedidos.model.Item;
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
public class ItemResponse {
    private UUID iditem;
    private UUID idproduto;
    private BigDecimal quantidade;

    public ItemResponse(Item item) {
        this.iditem = item.getIditem();
        this.idproduto = item.getIdproduto();
        this.quantidade = item.getQuantidade();
    }
}
