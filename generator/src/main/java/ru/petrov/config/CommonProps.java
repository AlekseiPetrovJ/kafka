package ru.petrov.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="generator")
@Data
public class CommonProps {
    private String producerUrl = "http://producer:8080";
}
