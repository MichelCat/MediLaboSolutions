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

	/**
	 * Creating gateway routes
	 *
	 * @param builder RouteLocatorBuilder
	 * @return RouteLocator
	 */
	//
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("microservice-frontend", r -> r.path("/patient/**")
						.uri("lb://microservice-frontend"))
				.route("microservice-frontend", r -> r.path("/note/**")
						.uri("lb://microservice-frontend"))

				.route("microservice-patients", r -> r.path("/patients/**")
						.uri("lb://microservice-patients"))
				.route("microservice-notes", r -> r.path("/notes/**")
						.uri("lb://microservice-notes"))
				.route("microservice-diabetes-risks", r -> r.path("/diabetes-risk/**")
						.uri("lb://microservice-diabetes-risks"))
				.build();
	}
}
