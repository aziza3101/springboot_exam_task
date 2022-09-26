package com.example.springboot_exam_task.exceptions;
import com.example.springboot_exam_task.dto.responses.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerNotFoundException(NotFoundException e) {
        return new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName()
        );
    }

    //400
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerBadRequestException(BadRequestException e) {
        return new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                e.getClass().getSimpleName()
        );
    }

    //403
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handlerForbiddenException(ForbiddenException e) {
        return new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName()
        );
    }
}