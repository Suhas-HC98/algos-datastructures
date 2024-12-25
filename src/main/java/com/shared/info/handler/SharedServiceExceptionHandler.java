package com.shared.info.handler;

import com.shared.info.exception.BadRequestException;
import com.shared.info.utils.GenericResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.shared.info.utils.SharedServiceResponseBuilder.wrapWithErrorResponse;

@RestControllerAdvice
public final class SharedServiceExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public GenericResponse<?> handleBadRequestException(BadRequestException badRequestException) {
        return wrapWithErrorResponse(badRequestException);
    }

    @ExceptionHandler(FeignException.class)
    public GenericResponse<?> handleFeignException(FeignException feignException) {
        return wrapWithErrorResponse(feignException);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
