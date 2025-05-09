package br.univesp.pi3.exception;


import org.springframework.http.HttpStatusCode;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseError handler(ValidationException ex) {
        return new ResponseError(HttpStatusCode.valueOf(422),
                ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public ResponseError handler(Exception ex) {
        return new ResponseError(HttpStatusCode.valueOf(500),
                "Erro interno. Por favor, contate a administração.",
                LocalDateTime.now());
    }

    @ExceptionHandler(MailException.class)
    public ResponseError handler(MailException ex) {
        return new ResponseError(HttpStatusCode.valueOf(502),
                "Erro no envio do e-mail. Por favor, tente novamente mais tarde.",
                LocalDateTime.now());
    }


}
