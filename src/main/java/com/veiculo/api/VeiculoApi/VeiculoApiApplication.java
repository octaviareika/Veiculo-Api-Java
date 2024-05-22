package com.veiculo.api.VeiculoApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class VeiculoApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VeiculoApiApplication.class, args);
	}

	public void run(String... args) throws IOException, InterruptedException {

		Principal principal = new Principal();
		principal.exibeMenu();

	}

}
