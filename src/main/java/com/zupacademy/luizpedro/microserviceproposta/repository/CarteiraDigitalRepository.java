package com.zupacademy.luizpedro.microserviceproposta.repository;

import com.zupacademy.luizpedro.microserviceproposta.model.CarteiraDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
    List<CarteiraDigital> findByCartaoNumeroCartao(String numeroCartao);
}
