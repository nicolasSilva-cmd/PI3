package br.univesp.pi3.exception;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public record ResponseError(HttpStatusCode statusCode, String message, LocalDateTime dateTime) {
}
