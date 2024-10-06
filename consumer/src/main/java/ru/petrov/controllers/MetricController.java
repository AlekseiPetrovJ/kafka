package ru.petrov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.petrov.dto.MetricDto;
import ru.petrov.model.exception.MetricNotFound;
import ru.petrov.service.MetricService;

@Slf4j
@RestController
@RequestMapping(path = "/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MetricController {
    public final MetricService metricService;

    @Operation(summary = "Получить все замеры")
    @GetMapping
    public ResponseEntity<Page<MetricDto>> getAllMetrics(@ParameterObject Pageable pageable){
        log.info("Was get request all metrics");
        return new ResponseEntity<>(metricService.findAll(pageable), HttpStatus.OK);

    }

    @Operation(summary = "Получить замер по id", description = """
            В переменной пути передается id замера.
            Возвращается замер с таким id. При отсутствии замера с таким id возвращается код ошибки 404.
            """)
    @GetMapping("/{id}")
    public ResponseEntity<MetricDto> getOne(@PathVariable Long id) {
        log.info("Get MetricDto with id {} ", id);
        try{
            return new ResponseEntity<>(metricService.findById(id),HttpStatus.OK);
        } catch (MetricNotFound e){
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
