package com.shared.info.service;

import com.shared.info.port.GreetPort;
import com.shared.info.service.impl.GreetServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GreetServiceImplTest {

    private final GreetPort port = mock(GreetPort.class);
    private final GreetServiceImpl service = new GreetServiceImpl(port);

    @Test
    void should_call_port_and_return_greeting_message_when_name_is_passed() {
        var name = "John";
        when(port.greet(name)).thenReturn("Hello " + name);
        var actualResponse = service.greet(name);
        assertTrue(actualResponse.contains(name));
        assertThat(actualResponse).isInstanceOf(String.class);
        verify(port).greet(name);
    }
}