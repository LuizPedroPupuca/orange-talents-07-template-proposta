package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.BiometriaRequest;
import com.zupacademy.luizpedro.microserviceproposta.dto.BloqueioRequest;
import com.zupacademy.luizpedro.microserviceproposta.model.Biometria;
import com.zupacademy.luizpedro.microserviceproposta.model.Bloqueio;
import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import com.zupacademy.luizpedro.microserviceproposta.model.StatusCartao;
import com.zupacademy.luizpedro.microserviceproposta.repository.BiometriaRepository;
import com.zupacademy.luizpedro.microserviceproposta.repository.BloqueioRepository;
import com.zupacademy.luizpedro.microserviceproposta.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartoesClient cartoesClient;



    @PostMapping("/{idCartao}/biometria")
    @Transactional
    public ResponseEntity<?> cadastraCartao(@PathVariable Long idCartao, @RequestBody @Valid BiometriaRequest biometriaRequest,
                                            UriComponentsBuilder builder){

        Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);

        if(cartaoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }

        Biometria biometria = biometriaRequest.toModel(cartaoOptional.get());
        biometriaRepository.save(biometria);
        URI uri = builder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{numeroCartao}/bloqueios")
    @Transactional
    public ResponseEntity<?> bloqueiaCartao(@PathVariable String numeroCartao, HttpServletRequest request,
                                            UriComponentsBuilder builder){

        Optional<Cartao> cartaoOptional = cartaoRepository.findByNumeroCartao(numeroCartao);

        String userClient = request.getHeader("User-Agent");
        String ipClient = request.getHeader("X-FORWARDED-FOR");
        if(ipClient == null)
            ipClient = request.getRemoteAddr();

        if(cartaoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }

        Cartao cartao = cartaoOptional.get();
        if(cartao.getStatus() == StatusCartao.bloqueado){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        Bloqueio bloqueio = new Bloqueio(ipClient, userClient, cartao);
        try {
            BloqueioRequest bloqueioRequest = new BloqueioRequest("Proposta");
            cartoesClient.bloqueiaCartao(numeroCartao,bloqueioRequest);
        }catch (FeignException e){
            System.out.println("excecao: "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        bloqueioRepository.save(bloqueio);
        cartao.bloqueioCartao();
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }
}
