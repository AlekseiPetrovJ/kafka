package ru.petrov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.petrov.config.CommonProps;
import ru.petrov.dto.MetricDto;
import ru.petrov.model.Metric;
import ru.petrov.utils.MetricMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessagingServiceImpl implements MessagingService {
    private final MetricService metricService;
    private final MetricMapper metricMapper;
    private final CommonProps commonProps;

    @KafkaListener(topics = "#{@commonProps.topic}")
    public void handle(MetricDto metricDto) {
        log.info("Get message {} from kafka", metricDto);
        Metric metric = metricService.save(metricMapper.metricDtoToMetric(metricDto));
        if (metric!=null){
            log.info("Saved metric: {}", metric);
        } else {
            log.error("Was not saved metricDto: {}", metricDto);
        }
    }
}
