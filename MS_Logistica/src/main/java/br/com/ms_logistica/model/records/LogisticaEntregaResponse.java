package br.com.ms_logistica.model.records;

import br.com.ms_logistica.model.LogisticaEntrega;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticaEntregaResponse {
    private UUID entregaId;
    private UUID pedido;
    private String cepEntrega;
    private String empresa;
    private String motorista;
    private Date dataCriacao;
    private int prazoDeEntrega;
    private Date dataEntrega;
    private String statusEntrega;

    public LogisticaEntregaResponse(LogisticaEntrega le) {
        setEntregaId(le.getEntregaId());
        setPedido(le.getPedido());
        setCepEntrega(le.getCepEntrega());
        setEmpresa(le.getEmpresa());
        setMotorista(le.getMotorista());
        setDataCriacao(le.getDataCriacao());
        setPrazoDeEntrega(le.getPrazoDeEntrega());
        setDataEntrega(le.getDataEntrega());
        setStatusEntrega(le.getStatusEntrega());
    }

}
