package br.com.ms_produto_estoque.configuration;

import br.com.ms_produto_estoque.exception.ProdutoEstoqueException;
import br.com.ms_produto_estoque.model.records.ProdutoRequest;
import br.com.ms_produto_estoque.service.ProdutoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ProdutoConsumer {

    @Autowired
    private ProdutoEstoqueService service;

    @Bean(name = "removerEstoque")
    Consumer<ProdutoRequest> consumer(){
        return produtoRequest -> {
            try {
                service.removerEstoque(produtoRequest.getId(),produtoRequest.getQuantidade());
            } catch (ProdutoEstoqueException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
