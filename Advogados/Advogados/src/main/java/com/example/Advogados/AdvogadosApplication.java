package com.example.Advogados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "App JuríConecta ", version =  "1", description = "API do aplicativo JuríConecta"))
public class AdvogadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvogadosApplication.class, args);
	}

}
