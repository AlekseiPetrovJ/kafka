package ru.petrov.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.petrov.config.CommonProps;
import ru.petrov.dto.MetricDto;
import ru.petrov.service.MessagingService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping(path = "/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class MetricProducerController {
    private final MessagingService messagingService;
    private final CommonProps props;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> createMessage(@RequestBody @Valid MetricDto metricDto) {
        try {
            CompletableFuture<SendResult<String, MetricDto>> future = messagingService
                    .send(props.getTopic(), metricDto);

            future.get(15, TimeUnit.SECONDS);
            log.info("MessageDto was CREATED. {}", metricDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("request: {}, exception {}", metricDto, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to send metrics", e);
        }
    }
}
