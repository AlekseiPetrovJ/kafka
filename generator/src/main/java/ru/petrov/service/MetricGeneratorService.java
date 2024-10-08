package ru.petrov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.petrov.config.CommonProps;
import ru.petrov.dto.MetricDto;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricGeneratorService {
    private final RestTemplate restTemplate;
    private final CommonProps commonProps;

    /**
     * Периодически запрашивает uptime и некоторые значения памяти. Вызывает метод по отправке Post запроса.
     */
    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    @Async("threadPoolTaskExecutor")
    public void getMetrics() {
        MetricDto uptime = new MetricDto(LocalDateTime.now(), "Uptime", "", "milliseconds",
                String.valueOf(ManagementFactory.getRuntimeMXBean().getUptime()));

        MemoryUsage heapMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        MetricDto heapMemoryInit = new MetricDto(LocalDateTime.now(), "heapMemoryInit",
                "the amount of memory in bytes that the Java virtual machine initially requests from the operating" +
                        " system for memory management. This method returns -1 if the initial memory size is undefined.",
                "bytes",
                String.valueOf(heapMemory.getInit()));

        MetricDto heapMemoryUsage = new MetricDto(LocalDateTime.now(), "heapMemoryUsage",
                "the amount of used memory in bytes", "bytes",
                String.valueOf(heapMemory.getUsed()));

        MetricDto heapMemoryCommited = new MetricDto(LocalDateTime.now(), "heapMemoryCommited",
                "the amount of memory in bytes that is committed for the Java virtual machine to use. This amount " +
                        "of memory is guaranteed for the Java virtual machine to use",
                "bytes",
                String.valueOf(heapMemory.getCommitted()));

        sendPostRequest(uptime);
        sendPostRequest(heapMemoryInit);
        sendPostRequest(heapMemoryUsage);
        sendPostRequest(heapMemoryCommited);
    }

    /**
     * Отправляет POST запрос с метрикой
     * @param metricDto
     */
    private void sendPostRequest(MetricDto metricDto) {
        String url = commonProps.getProducerUrl();
        System.out.println(metricDto);
        log.info("send metricDto {} to url {}", metricDto, url);
        restTemplate.postForEntity(url, metricDto, MetricDto.class);
    }
}