package ru.petrov.service;

import ru.petrov.dto.MetricDto;

public interface MessagingService {
    void handle(MetricDto emailMessageDto);
}