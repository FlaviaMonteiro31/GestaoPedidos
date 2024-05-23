package br.com.ms_produto_estoque.repository;


import br.com.ms_produto_estoque.model.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, UUID> {
}
