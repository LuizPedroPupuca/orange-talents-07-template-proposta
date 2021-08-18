package com.zupacademy.luizpedro.microserviceproposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class MicroservicePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePropostaApplication.class, args);
	}

}
