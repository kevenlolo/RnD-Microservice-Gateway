package com.rnd.pos.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	private static final String SECURITY_SERVICE = "/validateToken?token=";
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	@Override
	public GatewayFilter apply(Config config) {
		return (exhange, chain) -> {
			
			String authHeader = exhange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String[] parts = authHeader.split(" ");
			
			return webClientBuilder.build()
					.post()
					.uri(SECURITY_SERVICE + parts[1])
					.retrieve()
					.bodyToMono(String.class)
					.map(null);
		};
	}
	
	public static class Config {
		
	}
	
}
