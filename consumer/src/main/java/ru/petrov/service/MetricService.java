package ru.petrov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.petrov.model.Metric;
import ru.petrov.repositories.MetricRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MetricService {
    private final MetricRepository metricRepository;

    @Async
    @Transactional
    public Metric save(Metric metric) {
        return metricRepository.save(metric);
    }
}
