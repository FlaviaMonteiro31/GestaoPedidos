package br.com.ms_produto_estoque.controller;
import br.com.ms_produto_estoque.exception.ProdutoEstoqueException;
import br.com.ms_produto_estoque.model.records.ProdutoEstoqueResponse;
import br.com.ms_produto_estoque.model.records.ProdutoEstoqueResquest;
import br.com.ms_produto_estoque.service.ProdutoEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/produtoEstoque")
public class ProdutoEstoqueController {
    @Autowired
    private ProdutoEstoqueService service;
    @Operation(description = "Realiza do cadastro do produto e estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados do produto e estoque cadastrado"),
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ProdutoEstoqueResponse> inserirProdutoEstoque(@RequestBody @Valid ProdutoEstoqueResquest request)
            throws ProdutoEstoqueException {

        ProdutoEstoqueResponse response = service.inserirProdutoEstoque(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
    @Operation(description = "Consulta dados de produto por Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados do produto e estoque"),
            @ApiResponse(responseCode = "400", description = "Produto não localizado.")
    })
    @GetMapping("/lista/{id}")
    public ResponseEntity<ProdutoEstoqueResponse> listaPorId(@PathVariable UUID id)
            throws ProdutoEstoqueException {
        var pe = service.listaPorId(id);
        return ResponseEntity.ok(pe);
    }
    @Operation(description = "Consulta todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados de todos os produtos e estoque"),
    })
    @GetMapping(value = "/listaTodos")
    public Page<ProdutoEstoqueResponse> listarTodos (
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        Page<ProdutoEstoqueResponse> pe = service.listaTodos(pageRequest);
        return pe;
    }
    @Operation(description = "Atualiza dados do produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados do produto"),
            @ApiResponse(responseCode = "400", description = "Produto não localizado."),
    })
    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<ProdutoEstoqueResponse> atualizaProduto(@RequestBody @Valid ProdutoEstoqueResquest request, @PathVariable UUID id)
            throws ProdutoEstoqueException {
        var pe = service.atualizaProduto(request, id);
        return ResponseEntity.ok(pe);
    }

    @Operation(description = "Deleta produto e estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados deletados.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletaProduto(@PathVariable UUID id) throws ProdutoEstoqueException {
        service.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }

}
