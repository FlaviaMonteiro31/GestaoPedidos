package br.com.ms_clientes.controller;
import br.com.ms_clientes.exception.ClienteException;
import br.com.ms_clientes.model.records.ClienteRequest;
import br.com.ms_clientes.model.records.ClienteResponse;
import br.com.ms_clientes.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(description = "Realiza o cadastro do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados do cliente cadastrado"),
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ClienteResponse> inserirCliente (@RequestBody @Valid ClienteRequest request)
            throws ClienteException {

        ClienteResponse response = service.inserirCliente(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(response.getCpf()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(description = "Consulta dados cliente por Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados do cliente"),
            @ApiResponse(responseCode = "400", description = "Cliente não localizado.")
    })
    @GetMapping("/listaCliente/{id}")
    public ResponseEntity<ClienteResponse> listaClientePorId(@PathVariable UUID id)
            throws ClienteException {
        var cliente = service.listaClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(description = "Consulta dados de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados de todos os clientes"),
    })
    @GetMapping(value = "/listaTodos")
    public ResponseEntity<Page<ClienteResponse>> listarTodosClientes (
            @RequestParam(value =  "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ){
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var clientes = service.listaTodosClientes(pageRequest);
        return ResponseEntity.ok(clientes);
    }

    @Operation(description = "Atualiza dados do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados do cliente"),
            @ApiResponse(responseCode = "400", description = "Cliente não localizado."),
    })
    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@RequestBody @Valid ClienteRequest request, @PathVariable UUID id)
            throws ClienteException {
        var cliente = service.atualizaCliente(request, id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(description = "Deleta dados do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados deletados cliente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletaCliente(@PathVariable UUID id) throws ClienteException {
        service.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }
}
