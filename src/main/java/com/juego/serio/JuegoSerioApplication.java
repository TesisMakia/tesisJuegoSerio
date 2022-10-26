package com.juego.serio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JuegoSerioApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuegoSerioApplication.class, args);
	}

}
