package br.com.ms_produto_estoque.cargaMassiva;

import br.com.ms_produto_estoque.model.ProdutoEstoque;
import org.springframework.batch.item.ItemProcessor;

import java.util.UUID;

public class ProdutoEstoqueProcessor implements ItemProcessor<ProdutoEstoque,ProdutoEstoque> {
    @Override
    public ProdutoEstoque process(ProdutoEstoque pe) throws Exception {
        pe.setId(UUID.randomUUID());
        return pe;
    }
}
