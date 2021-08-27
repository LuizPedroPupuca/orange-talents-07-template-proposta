package com.zupacademy.luizpedro.microserviceproposta.repository;

import com.zupacademy.luizpedro.microserviceproposta.model.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
}
