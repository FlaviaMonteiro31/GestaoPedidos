package br.com.ms_pedidos.controller;

import br.com.ms_pedidos.exception.PedidoException;
import br.com.ms_pedidos.model.records.LogisticaPedidoRequest;
import br.com.ms_pedidos.model.records.LogisticaPedidoResponse;
import br.com.ms_pedidos.model.records.PedidoRequest;
import br.com.ms_pedidos.model.records.PedidoResponse;
import br.com.ms_pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Operation(description = "Gera o pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado"),
    })
    @PostMapping(value = "/geraPedido")
    public ResponseEntity<PedidoResponse> gerarPedido(@RequestBody @Valid PedidoRequest request)
            throws PedidoException {

        PedidoResponse response = service.gerarPedido(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping(value = "/preparaEntrega")
    public ResponseEntity<LogisticaPedidoResponse> preparaEntrega(@RequestBody @Valid LogisticaPedidoRequest request){
        return ResponseEntity.ok(service.preparaEntrega(request));
    }
}
