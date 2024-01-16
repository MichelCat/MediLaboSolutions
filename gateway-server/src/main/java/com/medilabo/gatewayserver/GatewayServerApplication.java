package com.medilabo.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	// Creating gateway routes
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
//				.route("microservice-frontend", r -> r.path("/patient/**")
//						.uri("http://localhost:8080/"))
//				.route("microservice-patients", r -> r.path("/patients/**")
//						.uri("http://localhost:9001/"))
				.route("microservice-frontend", r -> r.path("/patient/**")
						.uri("lb://microservice-frontend"))
				.route("microservice-patients", r -> r.path("/patients/**")
						.uri("lb://microservice-patients"))
				.build();
	}
}
