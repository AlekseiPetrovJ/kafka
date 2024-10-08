package ru.petrov.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricDto {
    @NotNull
    @Schema(description = "Дата и время")
    private LocalDateTime localDateTime;
    @Size(min = 2, max = 255, message = "name must be from 2 to 255 chars")
    @Schema(description = "Имя метрики", example = "Uptime")
    private String name;
    @NotNull
    @Schema(description = "Описание метрики", example = "Время с момента запуска приложения")
    private String description;
    @NotNull
    @Schema(description = "Единица измерения", example = "ms")
    private String baseUnit;
    @NotNull
    @Schema(description = "Значение замера", example = "1000")
    private String measurement;
}
