package com.example.demo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class QuoteStreamController {

    @MessageMapping("quotes")
    Flux<BidAsk> helloStream(String pair) {
        var halfSpread = new BigDecimal("0.00003");

        return Flux.interval(Duration.ofMillis(500))
                .map(x -> 1.0792 + ThreadLocalRandom.current().nextDouble(-1, 1) * 0.0006)
                .map(BigDecimal::valueOf)
                .map(mid -> new BidAsk(mid.subtract(halfSpread), mid.add(halfSpread)))
                .take(10);
    }

}
