package br.com.ms_produto_estoque.model.records;

import br.com.ms_produto_estoque.model.ProdutoEstoque;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoEstoqueResponse {

    private UUID id;
    private String nome;
    private String descricao;
    private String tamanho;
    private String cor;
    private BigDecimal preco;
    private BigDecimal quantidade;

    public ProdutoEstoqueResponse(ProdutoEstoque pe) {
        this.id = pe.getId();
        this.nome= pe.getNome();
        this.descricao = pe.getDescricao();
        this.tamanho = pe.getTamanho();
        this.cor = pe.getCor();
        this.preco = pe.getPreco();
        this.quantidade = pe.getQuantidade();
    }
}
