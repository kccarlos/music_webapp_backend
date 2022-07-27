package com.Element.Music.Config;

import com.Element.Music.IdProducer.OrderId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdConfig {
    @Bean
    public OrderId getIdWorker() {
        return new OrderId(1, 0);
    }
}
