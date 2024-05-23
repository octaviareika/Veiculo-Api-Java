package com.veiculo.api.VeiculoApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.veiculo.api.VeiculoApi.repository.RepositorioVeiculo;

import java.io.IOException;

@SpringBootApplication
public class VeiculoApiApplication implements CommandLineRunner {

	@Autowired
	RepositorioVeiculo repositorioVeiculo;
	
	public static void main(String[] args) {
		SpringApplication.run(VeiculoApiApplication.class, args);
	}

	public void run(String... args) throws IOException, InterruptedException {

		Principal principal = new Principal(repositorioVeiculo);
		principal.exibeMenu();

	}

}
