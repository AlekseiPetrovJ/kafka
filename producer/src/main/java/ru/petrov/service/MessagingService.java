package ru.petrov.service;

import org.springframework.kafka.support.SendResult;
import ru.petrov.dto.MetricDto;

import java.util.concurrent.CompletableFuture;

public interface MessagingService {
    CompletableFuture<SendResult<String, MetricDto>> send(String topic, MetricDto metricDto);
}
