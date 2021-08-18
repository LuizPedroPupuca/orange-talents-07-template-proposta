package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.ResultadoAnalise;
import com.zupacademy.luizpedro.microserviceproposta.dto.SolicitacaoAnalise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@FeignClient(name = "solicitacao", url = "${analise-financeira.host}")
public interface AnaliseFinanceira {

    @PostMapping("${analise-financeira.analisa-proposta}")
    ResultadoAnalise consultaDados(@RequestBody @Valid SolicitacaoAnalise solicitacaoAnalise);

}
