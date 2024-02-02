package com.medilabo.mfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Application is the class to run app
 *
 * @author MC
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients("com.medilabo.mfrontend")
@EnableDiscoveryClient
public class MicroserviceFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceFrontendApplication.class, args);
	}

}
