package com.library.zweibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.library.zweibrary")
@EntityScan("com.library.zweibrary")
public class ZweibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZweibraryApplication.class, args);
	}

}
