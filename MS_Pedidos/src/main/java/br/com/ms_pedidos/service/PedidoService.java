package br.com.ms_pedidos.service;

import br.com.ms_pedidos.enums.StatusPedidoEnum;
import br.com.ms_pedidos.exception.PedidoException;
import br.com.ms_pedidos.model.Endereco;
import br.com.ms_pedidos.model.Item;
import br.com.ms_pedidos.model.Pedido;
import br.com.ms_pedidos.model.records.PedidoRequest;
import br.com.ms_pedidos.model.records.PedidoResponse;
import br.com.ms_pedidos.repository.IEnderecoRepository;
import br.com.ms_pedidos.repository.IItemRepository;
import br.com.ms_pedidos.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository repository;
    @Autowired
    private IItemRepository itemRepository;
    @Autowired
    private IEnderecoRepository enderecoRepository;
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
            UUID produtoId = itemRequest.getIdproduto();
//            try {
//                //produtoRepository.findById(produtoId).orElseThrow(() -> new PedidoException("Produto não encontrado"));
//                //produtoEstoqueService.removerEstoque(produtoId, itemRequest.getQuantidade());
//            } catch (PedidoException e) {
//                throw new RuntimeException(e);
//          //  } catch (ProdutoEstoqueException e) {
//          //      throw new RuntimeException(e);
//            }

            Item item = new Item();
            item.setIdproduto(produtoId);
            item.setQuantidade(itemRequest.getQuantidade());
            item.setPedido(p);
            return item;
        }).collect(Collectors.toList());

        itemRepository.saveAll(itens);

        p.setItens(itens);

        return new PedidoResponse(pedido);
    }

}
