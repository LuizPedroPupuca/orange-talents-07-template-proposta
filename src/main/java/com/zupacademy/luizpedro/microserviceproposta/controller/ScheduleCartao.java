package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.CartaoApiResponse;
import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import com.zupacademy.luizpedro.microserviceproposta.model.Proposta;
import com.zupacademy.luizpedro.microserviceproposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ScheduleCartao {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CartoesClient cartoesClient;

    @Scheduled(fixedDelay = 50000) //executa a cada 50 segundos (tempo sempre em milisegundos)
    void associaCartao(){
        List<Proposta> propostasSemCartao = propostaRepository.findEligibleProposesWithoutCard();

        for (Proposta proposta: propostasSemCartao) {
            try {
                CartaoApiResponse cartaoApiResponse = cartoesClient.buscaCartaoParaAssociar(proposta.getId().toString());
                Cartao cartao = cartaoApiResponse.toModel();
                proposta.associaCartao(cartao);
                propostaRepository.save(proposta);
                System.out.println(cartaoApiResponse);
            }catch (FeignException e){
                System.out.println("Erro ao salvar cartao!");

            }
        }
    }
}
