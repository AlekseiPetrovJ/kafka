package ru.petrov.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricDto {
    @Schema(description = "Id замера")
    private Long id;
    @Schema(description = "Дата и время")
    private LocalDateTime localDateTime;
    @Schema(description = "Имя метрики", example = "Uptime")
    private String name;
    @Schema(description = "Описание метрики", example = "Время с момента запуска приложения")
    private String description;
    @Schema(description = "Единица измерения", example = "ms")
    private String baseUnit;
    @Schema(description = "Значение замера", example = "1000")
    private String measurement;
}
