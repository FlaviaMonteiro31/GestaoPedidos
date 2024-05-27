package br.com.ms_pedidos.service;

import br.com.ms_pedidos.enums.StatusPedidoEnum;
import br.com.ms_pedidos.exception.PedidoException;
import br.com.ms_pedidos.gateway.LogisticaGateway;
import br.com.ms_pedidos.gateway.RemoverEstoqueGateway;
import br.com.ms_pedidos.model.Endereco;
import br.com.ms_pedidos.model.Item;
import br.com.ms_pedidos.model.Pedido;
import br.com.ms_pedidos.model.records.*;
import br.com.ms_pedidos.repository.IEnderecoRepository;
import br.com.ms_pedidos.repository.IItemRepository;
import br.com.ms_pedidos.repository.IPedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private RemoverEstoqueGateway gateway;
    @Autowired
    private IPedidoRepository repository;
    @Autowired
    private IItemRepository itemRepository;
    @Autowired
    private IEnderecoRepository enderecoRepository;
    @Autowired
    private LogisticaGateway logisticaGateway;
    private StatusPedidoEnum status;

    public PedidoResponse gerarPedido(PedidoRequest request) throws PedidoException {

        Endereco endereco = new Endereco();
        endereco.setCep(request.getEnderecoEntrega().getCep());
        endereco.setCidade(request.getEnderecoEntrega().getCidade());
        endereco.setEstado(request.getEnderecoEntrega().getEstado());
        endereco.setLogradouro(request.getEnderecoEntrega().getLogradouro());
        endereco.setNumero(request.getEnderecoEntrega().getNumero());
        endereco.setComplemento(request.getEnderecoEntrega().getComplemento());
        endereco = enderecoRepository.save(endereco);

        Pedido pedido = new Pedido();
        pedido.setCliente(request.getCliente());
        pedido.setStatus(status.PROCESSANDO_PEDIDO.toString());
        pedido.setEnderecoEntrega(endereco);

        Pedido p = repository.save(pedido);

        List<Item> itens = request.getItens().stream().map(itemRequest -> {
            gateway.removerEstoque(new GenericMessage<>(new RemoverEstoqueRequest(itemRequest.getIdproduto(), itemRequest.getQuantidade())));

            Item item = new Item();
            item.setIdproduto(itemRequest.getIdproduto());
            item.setQuantidade(itemRequest.getQuantidade());
            item.setPedido(p);
            return item;
        }).collect(Collectors.toList());

        itemRepository.saveAll(itens);

        p.setItens(itens);

        return new PedidoResponse(pedido);
    }

    public LogisticaPedidoResponse preparaEntrega(@Valid LogisticaPedidoRequest request){
     return logisticaGateway.logistica(new GenericMessage<>(request));
    }

}
