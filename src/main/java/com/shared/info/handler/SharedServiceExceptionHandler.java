package com.shared.info.handler;

import com.shared.info.exception.BadRequestException;
import com.shared.info.utils.GenericResponse;
import feign.FeignException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
