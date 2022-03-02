package com.rnd.pos.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public DiscoveryClientRouteDefinitionLocator discoveryClientRouteLocator(ReactiveDiscoveryClient discoveryClient,
			DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(discoveryClient, dlp);
	}

	@Bean
	public WebClient.Builder webClient() {
		return WebClient.builder();
	}

}