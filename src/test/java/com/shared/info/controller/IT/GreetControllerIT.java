package com.shared.info.controller.IT;

import com.shared.info.AutoConfigureTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTest
class GreetControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_greetings_when_name_is_passed() throws Exception {
        var greetings = mockMvc.perform(get("/graphql/greet")
                .param("name", "John")
                .contentType(APPLICATION_JSON));
        greetings.andExpect(status().is2xxSuccessful());
        greetings.andExpect(jsonPath("$").value("Hello John"));
    }
}