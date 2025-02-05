package com.perrut.gestao_eventos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestaoEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoEventosApplication.class, args);
	}

}
