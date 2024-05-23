package br.com.ms_pedidos.repository;

import br.com.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPedidoRepository extends JpaRepository<Pedido, UUID> {
}
