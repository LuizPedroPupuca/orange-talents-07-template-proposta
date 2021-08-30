package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.PropostaRequest;
import com.zupacademy.luizpedro.microserviceproposta.dto.PropostaResponse;
import com.zupacademy.luizpedro.microserviceproposta.dto.ResultadoAnalise;
import com.zupacademy.luizpedro.microserviceproposta.dto.SolicitacaoAnalise;
import com.zupacademy.luizpedro.microserviceproposta.model.Proposta;
import com.zupacademy.luizpedro.microserviceproposta.model.Status;
import com.zupacademy.luizpedro.microserviceproposta.repository.PropostaRepository;
import feign.FeignException;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private Tracer tracer;


    @PostMapping
    @Transactional
    ResponseEntity<?> cadastra(@RequestBody @Valid PropostaRequest propostaRequest,
                                  UriComponentsBuilder builder){
        tracer.activeSpan().setTag("user.email",propostaRequest.getEmail());
        Optional<Proposta> propostaOptional = propostaRepository
                .findByDocumento(propostaRequest.getDocumento());
        if(propostaOptional.isEmpty()){
            Proposta proposta = propostaRequest.toModel();
            propostaRepository.save(proposta);
            try{
                SolicitacaoAnalise solicitacaoAnalise = new SolicitacaoAnalise(proposta);
                ResultadoAnalise resultadoAnalise = analiseFinanceira.consultaDados(solicitacaoAnalise);
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

    @GetMapping("/{id}")
    ResponseEntity<?> consultaProposta(@PathVariable Long id){
       Optional<Proposta> propostaOptional = propostaRepository.findById(id);
        if(propostaOptional.isPresent()){
            Proposta proposta = propostaOptional.get();
            return ResponseEntity.ok().body(new PropostaResponse(proposta));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");

    }




}
