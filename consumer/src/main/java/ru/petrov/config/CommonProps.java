package ru.petrov.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-consumer")
@Data
public class CommonProps {
    private String topic = "metrics-topic";
}