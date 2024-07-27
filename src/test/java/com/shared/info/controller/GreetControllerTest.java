package com.shared.info.controller;

import com.shared.info.service.GreetService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GreetControllerTest {

    private final GreetService service = mock(GreetService.class);
    private final GreetController controller = new GreetController(service);

    @Test
    void should_return_greetings_when_name_is_passed() {
        var name = "John";
        when(service.greet(name)).thenReturn("Hello " + name);
        String actualResponse = controller.greet(name);
        assertTrue(actualResponse.contains(name));
        verify(service).greet(name);
    }
}