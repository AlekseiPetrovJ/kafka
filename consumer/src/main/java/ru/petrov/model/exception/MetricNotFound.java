package ru.petrov.model.exception;

public class MetricNotFound extends RuntimeException{
    public MetricNotFound() {
    }

    public MetricNotFound(String message) {
        super(message);
    }
}
