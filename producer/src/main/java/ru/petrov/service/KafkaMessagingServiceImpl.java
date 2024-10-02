package ru.petrov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.petrov.dto.MetricDto;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaMessagingServiceImpl implements MessagingService {
    private final KafkaTemplate<String, MetricDto> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, MetricDto>> send(String topic, MetricDto metricDto) {
        return kafkaTemplate.send(topic, metricDto);
    }
}

