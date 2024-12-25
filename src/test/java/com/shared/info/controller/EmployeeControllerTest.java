package com.shared.info.controller;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.shared.info.utils.Constants.NON_PERSISTABLE;
import static com.shared.info.utils.Constants.PERSISTABLE;
import static com.shared.info.vo.TestUtils.employee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    private final Validator validator = mock(Validator.class);
    private final BindingResult bindingResult = mock(BindingResult.class);
    private final Errors errors = mock(Errors.class);
    private final EmployeeController controller = new EmployeeController(validator);

    @Test
    void should_return_success_message_with_check1_when_proper_requestBody_is_passed() {
        assertEquals(PERSISTABLE, controller.checkAndPersist(employee()));
        verify(bindingResult, never()).hasErrors();
    }

    @Test
    void should_return_success_message_with_check2_when_proper_requestBody_is_passed() {
        when(bindingResult.hasErrors()).thenReturn(false);
        assertEquals(PERSISTABLE, controller.bindingResultCheckAndPersist(employee(), bindingResult));
        verify(bindingResult).hasErrors();
    }

    @Test
    void should_return_failure_message_with_check2_when_proper_requestBody_is_passed() {
        when(bindingResult.hasErrors()).thenReturn(true);
        assertEquals(NON_PERSISTABLE, controller.bindingResultCheckAndPersist(employee(), bindingResult));
        verify(bindingResult).hasErrors();
    }

    @Test
    void should_return_success_message_with_check3_when_proper_requestBody_is_passed() {
        var emp = employee();
        when(errors.hasErrors()).thenReturn(false);
        when(validator.validateObject(emp)).thenReturn(errors);
        assertEquals(PERSISTABLE, controller.validatorCheckAndPersist(emp));
        verify(bindingResult, never()).hasErrors();
        verify(errors).hasErrors();
        verify(validator).validateObject(emp);
    }

    @Test
    void should_return_failure_message_with_check3_when_proper_requestBody_is_passed() {
        var emp = employee();
        when(errors.hasErrors()).thenReturn(true);
        when(validator.validateObject(emp)).thenReturn(errors);
        assertEquals(NON_PERSISTABLE, controller.validatorCheckAndPersist(emp));
        verify(bindingResult, never()).hasErrors();
        verify(errors).hasErrors();
        verify(validator).validateObject(emp);
    }
}