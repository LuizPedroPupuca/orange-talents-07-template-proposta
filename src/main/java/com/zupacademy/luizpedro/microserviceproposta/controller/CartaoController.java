package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.BiometriaRequest;
import com.zupacademy.luizpedro.microserviceproposta.model.Biometria;
import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import com.zupacademy.luizpedro.microserviceproposta.repository.BiometriaRepository;
import com.zupacademy.luizpedro.microserviceproposta.repository.CartaoRepository;
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
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;



    @PostMapping("/biometria/{idCartao}")
    @Transactional
    public ResponseEntity<?> cadastraCartao(@PathVariable Long idCartao, @RequestBody @Valid BiometriaRequest biometriaRequest,
                                            UriComponentsBuilder builder){

        Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);

        if(cartaoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }

        Biometria biometria = biometriaRequest.toModel(cartaoOptional.get());
        biometriaRepository.save(biometria);
        URI uri = builder.path("/proposta/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
