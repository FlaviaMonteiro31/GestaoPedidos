package br.com.ms_logistica.configuration;

import br.com.ms_logistica.model.records.LogisticaEntregaRequest;
import br.com.ms_logistica.model.records.LogisticaEntregaResponse;
import br.com.ms_logistica.service.LogisticaEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class LogisticaConsumer {

    @Autowired
    private LogisticaEntregaService service;

    @Bean(name = "preparaEntrega")
    Function<LogisticaEntregaRequest, LogisticaEntregaResponse> consumer(){
        return ler -> {
             return service.preparaEntrega(ler);
        };
    }

}
