package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.PropostaRequest;
import com.zupacademy.luizpedro.microserviceproposta.dto.ResultadoAnalise;
import com.zupacademy.luizpedro.microserviceproposta.dto.SolicitacaoAnalise;
import com.zupacademy.luizpedro.microserviceproposta.model.Proposta;
import com.zupacademy.luizpedro.microserviceproposta.model.Status;
import com.zupacademy.luizpedro.microserviceproposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseFinanceira analiseFinanceira;


    @PostMapping
    @Transactional
    ResponseEntity<?> cadastra(@RequestBody @Valid PropostaRequest propostaRequest,
                                  UriComponentsBuilder builder){
        Optional<Proposta> propostaOptional = propostaRepository
                .findByDocumento(propostaRequest.getDocumento());
        if(propostaOptional.isEmpty()){
            Proposta proposta = propostaRequest.toModel();
            propostaRepository.save(proposta);
            try{
                SolicitacaoAnalise solicitacaoAnalise = new SolicitacaoAnalise(proposta);
                ResultadoAnalise resultadoAnalise = analiseFinanceira.consultaDados(solicitacaoAnalise);
                System.out.println(resultadoAnalise);
                System.out.println(resultadoAnalise.toString());
                proposta.atualizaStatus(resultadoAnalise.getResultadoSolicitacao());
                System.out.println();
            }catch (FeignException e){
                proposta.atualizaStatus(Status.COM_RESTRICAO);
            }
            propostaRepository.save(proposta);
            URI uri = builder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(422).body("Já existe um CNPJ/CPF cadastrado");

    }




}
