package br.com.ms_pedidos.repository;


import br.com.ms_pedidos.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEnderecoRepository extends JpaRepository<Endereco, UUID> {

}
