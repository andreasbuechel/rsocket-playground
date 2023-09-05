package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class RSocketController {

    @MessageMapping("req-res")
    String hello(String name) {
        return "Hello, %s".formatted(name);
    }

    @MessageMapping("req-strm")
    Flux<String> helloStream(String name) {
        return Flux.just("Hello, %s".formatted(name), "Guguus");
    }

}

