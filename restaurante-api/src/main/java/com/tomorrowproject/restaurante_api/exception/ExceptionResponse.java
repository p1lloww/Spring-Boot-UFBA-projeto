package com.tomorrowproject.restaurante_api.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String message, String details) {}
