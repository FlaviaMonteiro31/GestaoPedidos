package br.com.ms_logistica.service;

import br.com.ms_logistica.enuns.StatusEntregaEnum;
import br.com.ms_logistica.model.CalculaEntrega;
import br.com.ms_logistica.model.LogisticaEntrega;
import br.com.ms_logistica.model.records.LogisticaEntregaRequest;
import br.com.ms_logistica.model.records.LogisticaEntregaResponse;
import br.com.ms_logistica.repository.ILogisticaEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class LogisticaEntregaService {

    @Autowired
    private ILogisticaEntregaRepository repository;
    StatusEntregaEnum status;

    public LogisticaEntregaResponse preparaEntrega(LogisticaEntregaRequest request){
        CalculaEntrega ce = calculaEntrega();

        LogisticaEntrega entrega = new LogisticaEntrega();
        entrega.setPedido(request.getPedido());
        entrega.setCepEntrega(request.getCepEntrega());
        entrega.setEmpresa(selecionaEmpresaDisponivel());
        entrega.setMotorista(selecionaMotorista());
        entrega.setDataCriacao(ce.getDataCriacao());
        entrega.setPrazoDeEntrega(ce.getPrazoDeEntrega());
        entrega.setDataEntrega(ce.getDataEntrega());
        entrega.setStatusEntrega(status.PREPARANDO_ENVIO.toString());

        LogisticaEntrega le = repository.save(entrega);
        return new LogisticaEntregaResponse(le);
    }

    public String selecionaEmpresaDisponivel(){
        return "RápidoExpress Entrega ME";
    }

    public String selecionaMotorista(){
        return "Fulano de Tal";
    }
    public Date getDataAtual(){
        Date dataAtual = new Date();
        return dataAtual;
    }
    public int getPrazoEntrega(){
        Random random = new Random();
        int prazo = random.nextInt(1, 20);
        return prazo;
    }
    public CalculaEntrega calculaEntrega(){
        Date date = getDataAtual();
        int prazo = getPrazoEntrega();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, prazo);

        CalculaEntrega ce = new CalculaEntrega();
        ce.setDataCriacao(date);
        ce.setPrazoDeEntrega(prazo);
        ce.setDataEntrega(calendar.getTime());

        return ce;
    }

}
