package com.shared.info.controller;

import org.junit.jupiter.api.Test;

import static com.shared.info.vo.TestUtils.employee;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeControllerTest {

    private final EmployeeController controller = new EmployeeController();

    @Test
    void should_return_success_message_when_proper_requestBody_is_passed() {
        assertEquals("Provided request body is valid for persistence", controller.save(employee()));
    }
}