package br.com.ms_clientes.model.records;

import br.com.ms_clientes.model.Cliente;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @CPF
    private String cpf;
    @NotNull
    private String nome;

    private String telefone;
    private String email;

    public Cliente toCliente() {
        return new Cliente(this.cpf,this.nome, this.telefone, this.email);
    }

}
