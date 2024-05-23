package br.com.ms_pedidos.model.records;

import br.com.ms_pedidos.model.Endereco;
import br.com.ms_pedidos.model.Item;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {

    private UUID cliente;
    @NotNull
    private List<Item> itens;
    private Endereco enderecoEntrega;
}
