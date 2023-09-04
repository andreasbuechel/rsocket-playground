package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Controller
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@MessageMapping("rfs")
	public Double foo(String pair) {
		return 1.2;
	}

	@Bean
	public RSocketMessageHandler rSocketMessageHandler(RSocketStrategies strategies) {
		var handler = new RSocketMessageHandler();
		handler.setRSocketStrategies(strategies);
		return handler;
	}

	@Bean
	public RSocketStrategies rSocketStrategies() {
		return RSocketStrategies.builder()
				.encoders(es -> es.add(new Jackson2CborEncoder()))
				.decoders(ss -> ss.add(new Jackson2CborDecoder()))
				.build();
	}

}
