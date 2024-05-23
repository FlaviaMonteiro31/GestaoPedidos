package br.com.ms_clientes.service;

import br.com.ms_clientes.exception.ClienteException;
import br.com.ms_clientes.model.Cliente;
import br.com.ms_clientes.model.records.ClienteRequest;
import br.com.ms_clientes.model.records.ClienteResponse;
import br.com.ms_clientes.repository.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    public ClienteResponse inserirCliente(ClienteRequest request) throws ClienteException {

        Cliente cliente = new Cliente();
        if(!(repository.findByCpf(cliente.getCpf()).isEmpty())){
           throw new ClienteException("O CPF informado já existe!");
        }

        cliente.setCpf(request.getCpf());
        cliente.setNome(request.getNome());
        cliente.setTelefone(request.getTelefone());
        cliente.setEmail(request.getEmail());
        Cliente sc = repository.save(cliente);
        return new ClienteResponse(sc);
    }

    public Page<ClienteResponse> listaTodosClientes(PageRequest pagina){
        var clientes = repository.findAll(pagina);
        return clientes.map(c -> new ClienteResponse(c));
    }

    public ClienteResponse listaClientePorId(UUID clienteId) throws ClienteException {
        var cliente = repository.findById(clienteId).orElseThrow(() -> new ClienteException("Cliente não localizado."));
    return new ClienteResponse(cliente);
    }
    public Cliente buscaClienteId(UUID clienteId) throws ClienteException {
        var cliente = repository.findById(clienteId).orElseThrow(() -> new ClienteException("Cliente não localizado."));
        return cliente;
    }

    public ClienteResponse atualizaCliente(ClienteRequest request, UUID id) throws ClienteException {
        try {
            Cliente cliente = repository.getOne(id);
            cliente.setCpf(request.getCpf());
            cliente.setNome(request.getNome());
            cliente.setTelefone(request.getTelefone());
            cliente.setEmail(request.getEmail());
            Cliente sc = repository.save(cliente);
            return new ClienteResponse(sc);
        } catch (Exception e) {
            throw  new ClienteException("Cliente não encontrado, id:" + id);
        }
    }

    public void deletaCliente(UUID id) throws ClienteException {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entity not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new  ClienteException("Violação de integridade da base");
        }

    }

}
