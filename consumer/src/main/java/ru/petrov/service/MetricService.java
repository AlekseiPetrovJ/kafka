package ru.petrov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.petrov.dto.MetricDto;
import ru.petrov.model.Metric;
import ru.petrov.model.exception.MetricNotFound;
import ru.petrov.repositories.MetricRepository;
import ru.petrov.utils.MetricMapper;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MetricService {
    private final MetricRepository metricRepository;
    private final MetricMapper metricMapper;

    @Async
    @Transactional
    public Metric save(Metric metric) {
        return metricRepository.save(metric);
    }

    public Page<MetricDto> findAll(Pageable pageable) {
        return metricRepository.findAll(pageable)
                .map(metricMapper::metricToMetricDto);
    }

    public MetricDto findById(Long id){
        Metric metric = metricRepository.findById(id).orElseThrow(() ->
                new MetricNotFound(String.format("Metric with ID:%s not found", id)));
        return metricMapper.metricToMetricDto(metric);
    }
}