package com.shared.info.handler;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static com.shared.info.vo.TestUtils.badRequestException;
import static com.shared.info.vo.TestUtils.feignException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SharedServiceExceptionHandlerTest {

    private final SharedServiceExceptionHandler handler = new SharedServiceExceptionHandler();

    @Test
    void should_call_handleBadRequestException_when_incorrect_request_is_triggered() {
        var response = handler.handleBadRequestException(badRequestException());
        assertNotNull(response);
        assertFalse(response.metaData().isSuccess());
        assertEquals(1, response.data().size());
    }

    @Test
    void should_call_handleFeignException_when_incorrect_request_is_triggered() {
        var response = handler.handleFeignException(feignException());
        assertNotNull(response);
        assertFalse(response.metaData().isSuccess());
        assertEquals(1, response.data().size());
    }

    @Test
    void should_call_handleValidationExceptions_when_incorrect_request_is_triggered() {
        var bindingResult = mock(BindingResult.class);
        var fieldError1 = new FieldError("clientEntitlement", "id",
                "id must not be empty");
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError1));
        var exception = new MethodArgumentNotValidException(null, bindingResult);
        var actualErrors = handler.handleValidationExceptions(exception);
        var expectedErrors = Map.of("id", "id must not be empty");
        assertEquals(expectedErrors, actualErrors, "The validation errors do not match!");
    }
}