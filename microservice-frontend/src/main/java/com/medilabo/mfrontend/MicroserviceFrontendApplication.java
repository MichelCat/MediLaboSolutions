package com.medilabo.mfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.medilabo.mfrontend")
public class MicroserviceFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceFrontendApplication.class, args);
	}

}
