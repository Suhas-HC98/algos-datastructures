package com.shared.info.adapter;

import com.shared.info.port.GreetPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {GreetAdapter.class})
class GreetAdapterTest {

    @Autowired
    private GreetPort greetPort;

    @MockBean(name = "greetService")
    private GraphQlClient graphQlClient;

    @Test
    void should_return_greetings_when_name_is_passed() {
        var documentName = "greet-user";
        var variableName = "name";
        var retrievePath = "greeting";
        var inputName = "John";
        var expectedResponse = "Hello " + inputName;

        GraphQlClient.RequestSpec requestSpec = mock(GraphQlClient.RequestSpec.class);
        GraphQlClient.RetrieveSpec retrieveSpec = mock(GraphQlClient.RetrieveSpec.class);
        when(graphQlClient.documentName(documentName)).thenReturn(requestSpec);
        when(requestSpec.variable(variableName, inputName)).thenReturn(requestSpec);
        when(requestSpec.retrieve(retrievePath)).thenReturn(retrieveSpec);
        when(retrieveSpec.toEntity(String.class)).thenReturn(Mono.just(expectedResponse));

        var greetings = greetPort.greet(inputName);
        assertNotNull(greetings);
        assertEquals(expectedResponse, greetings);
        verify(graphQlClient).documentName(documentName);
    }

}