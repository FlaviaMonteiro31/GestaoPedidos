package br.com.ms_pedidos.gateway;

import br.com.ms_pedidos.model.records.RemoverEstoqueRequest;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@MessagingGateway
public interface RemoverEstoqueGateway {

    @Gateway(requestChannel = "estoque",
            requestTimeout = 5000,
            headers = @GatewayHeader(name = MessageHeaders.REPLY_CHANNEL, expression = "@nullChannel"))
    void removerEstoque(Message<RemoverEstoqueRequest> removerEstoque);
}
