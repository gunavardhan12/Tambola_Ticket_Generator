package com.example.tambola.ticket.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TambolaTicketGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TambolaTicketGeneratorApplication.class, args);
	}

}
