package ru.petrov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricDto {
    private Long id;
    private LocalDateTime localDateTime;
    private String name;
    private String description;
    private String baseUnit;
    private String measurement;
}
