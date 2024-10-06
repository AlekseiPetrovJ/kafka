package ru.petrov.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.petrov.dto.MetricDto;
import ru.petrov.model.Metric;

@Component
@RequiredArgsConstructor
public class MetricMapper {
    private final ModelMapper modelMapper;

    public Metric metricDtoToMetric(MetricDto metricDto){
        return modelMapper.map(metricDto, Metric.class);
    }

    public MetricDto metricToMetricDto(Metric metric){
        return modelMapper.map(metric, MetricDto.class);
    }
}