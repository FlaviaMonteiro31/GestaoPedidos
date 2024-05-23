package br.com.ms_logistica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculaEntrega {
    private Date dataCriacao;
    private int prazoDeEntrega;
    private Date dataEntrega;
}
