package com.shared.info.service;

import com.shared.info.dto.ClientDetails;
import com.shared.info.service.impl.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final ClientService clientService = mock(ClientService.class);

    private final UserService userService = new UserService(clientService);

    @ParameterizedTest(name = "{index} - testing for {0} expecting {1}")
    @MethodSource("getSigmaCodeInputs")
    void should_return_accurate_signma_code_when_segment_and_sub_segment_are_passed(ClientDetails clientDetails, String expectedResultString) {
        when(clientService.getClientDetails("123")).thenReturn(clientDetails);

        var sigmaCode = userService.getSigmaCode("123");
        assertEquals(expectedResultString, sigmaCode);
        verify(clientService).getClientDetails("123");
    }

    static Stream<Arguments> getSigmaCodeInputs() {
        return Stream.of(
                Arguments.of(ClientDetails.builder().segment("A1").subSegment("A2").build(), "AWT"),
                Arguments.of(ClientDetails.builder().segment("W1").subSegment("W2").build(), "WWT"),
                Arguments.of(ClientDetails.builder().segment("R1").subSegment("R2").build(), "RWT")
        );
    }
}