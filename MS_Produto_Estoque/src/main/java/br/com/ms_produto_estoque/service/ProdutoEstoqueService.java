package br.com.ms_produto_estoque.service;

import br.com.ms_produto_estoque.exception.ProdutoEstoqueException;
import br.com.ms_produto_estoque.model.ProdutoEstoque;
import br.com.ms_produto_estoque.model.records.ProdutoEstoqueResponse;
import br.com.ms_produto_estoque.model.records.ProdutoEstoqueResquest;
import br.com.ms_produto_estoque.repository.IProdutoEstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProdutoEstoqueService {

    @Autowired
    private IProdutoEstoqueRepository repository;
    public ProdutoEstoqueResponse inserirProdutoEstoque(ProdutoEstoqueResquest request) throws ProdutoEstoqueException {

        ProdutoEstoque pe = new ProdutoEstoque();
        pe.setNome(request.getNome());
        pe.setDescricao(request.getDescricao());
        pe.setTamanho(request.getTamanho());
        pe.setCor(request.getCor());
        pe.setPreco(request.getPreco());
        pe.setQuantidade(request.getQuantidade());

        ProdutoEstoque produtoEstoque = repository.save(pe);
        return new ProdutoEstoqueResponse(produtoEstoque);
    }

    public Page<ProdutoEstoqueResponse> listaTodos(PageRequest pagina){
        var pe = repository.findAll(pagina);
        return pe.map(p -> new ProdutoEstoqueResponse(p));
    }

    public ProdutoEstoqueResponse listaPorId(UUID produtoId) throws ProdutoEstoqueException {
        var pe = repository.findById(produtoId).orElseThrow(() -> new ProdutoEstoqueException("Produto não localizado."));
        return new ProdutoEstoqueResponse(pe);
    }
    public void deletaProduto(UUID id) throws ProdutoEstoqueException {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entity not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new  ProdutoEstoqueException("Violação de integridade da base");
        }
    }

    public ProdutoEstoqueResponse atualizaProduto(ProdutoEstoqueResquest request, UUID id) throws ProdutoEstoqueException {
        try {
            ProdutoEstoque pe = repository.getOne(id);
            pe.setNome(request.getNome());
            pe.setDescricao(request.getDescricao());
            pe.setTamanho(request.getTamanho());
            pe.setCor(request.getCor());
            pe.setPreco(request.getPreco());
            ProdutoEstoque produtoEstoque = repository.save(pe);
            return new ProdutoEstoqueResponse(produtoEstoque);
        } catch (Exception e) {
            throw  new ProdutoEstoqueException("Produto não encontrado, id:" + id);
        }
    }
    public void removerEstoque(UUID id, BigDecimal quantidade) throws ProdutoEstoqueException {
        ProdutoEstoque produtoLocalizado = repository.findById(id).orElseThrow(() -> new ProdutoEstoqueException("Produto não localizado"));
        produtoLocalizado.setQuantidade(getFinalEstoqueQuantidade(produtoLocalizado.getQuantidade(), quantidade));
        repository.save(produtoLocalizado);
    }

    private BigDecimal getFinalEstoqueQuantidade(BigDecimal quantiadadeDisponivel, BigDecimal quantidadeSolicitada) {
        final BigDecimal novaQuantidade = quantiadadeDisponivel.subtract(quantidadeSolicitada);
        if (novaQuantidade.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Estoque insuficiente para a compra desejada.");
        } else {
            return novaQuantidade;
        }
    }

}
