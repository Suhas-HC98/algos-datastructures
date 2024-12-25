package com.shared.info.controller.IT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class HealthCheckControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "SUHAS", roles = {"ADMIN"})
    void should_trigger_health_check_api_and_return_valid_response() throws Exception {
        var actualResponse = mockMvc.perform(get("/health/health-check"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void should_throw_exception_when_anonymous_user_trigger_health_check_api() throws Exception {
        var actualResponse = mockMvc.perform(get("/health/health-check"))
                .andExpect(status().isForbidden())
                .andReturn();
        assertEquals(HttpStatus.FORBIDDEN.value(), actualResponse.getResponse().getStatus());
    }
}
