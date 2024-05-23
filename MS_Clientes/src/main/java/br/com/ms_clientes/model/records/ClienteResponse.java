package br.com.ms_clientes.model.records;
import br.com.ms_clientes.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ClienteResponse {
    private UUID clientId;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;

    public ClienteResponse(Cliente cliente) {
        this.clientId = cliente.getClientId();
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
    }
    public ClienteResponse toClienteResponse(Cliente cliente) {

        setCpf(cliente.getCpf());
        setNome(cliente.getNome());
        setTelefone(cliente.getTelefone());
        setEmail(cliente.getEmail());
        return this;
    }
}
