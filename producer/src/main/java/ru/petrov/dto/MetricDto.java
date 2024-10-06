package ru.petrov.dto;

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
    private LocalDateTime localDateTime;
    @Size(min = 2, max = 255, message = "name must be from 2 to 255 chars")
    private String name;
    private String description;
    @NotNull
    private String baseUnit;
    @NotNull
    private String measurement;
}
