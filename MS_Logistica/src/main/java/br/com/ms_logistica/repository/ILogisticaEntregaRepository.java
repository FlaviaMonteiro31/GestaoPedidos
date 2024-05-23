package br.com.ms_logistica.repository;

import br.com.ms_logistica.model.LogisticaEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ILogisticaEntregaRepository extends JpaRepository<LogisticaEntrega, UUID> {
}
