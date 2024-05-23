package br.com.ms_logistica.controller;

import br.com.ms_logistica.exception.LogisticaEntregaException;
import br.com.ms_logistica.model.records.LogisticaEntregaRequest;
import br.com.ms_logistica.model.records.LogisticaEntregaResponse;
import br.com.ms_logistica.service.LogisticaEntregaService;
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
@RequestMapping("/logisticaEntrega")
public class LogisticaEntregaController {

    @Autowired
    private LogisticaEntregaService service;


    @Operation(description = "Gera a logistica e prazo para entrega do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logisticia e prazo de entrega criados"),
    })
    @PostMapping(value = "/calcular")
    public ResponseEntity<LogisticaEntregaResponse> gerarPedido(@RequestBody @Valid LogisticaEntregaRequest request)
            throws LogisticaEntregaException {

        LogisticaEntregaResponse response = service.preparaEntrega(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getEntregaId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
