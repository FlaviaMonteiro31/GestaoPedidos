package br.com.ms_pedidos;

import br.com.ms_pedidos.model.records.LogisticaPedidoRequest;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@MessagingGateway
public interface LogisticaGateway {

    @Gateway(requestChannel = "logistica",
            requestTimeout = 5000,
            headers = @GatewayHeader(name = MessageHeaders.REPLY_CHANNEL))
    void preparaEntrega(Message<LogisticaPedidoRequest> preparaEntrega);
}
