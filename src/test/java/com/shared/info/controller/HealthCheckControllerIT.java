package com.shared.info.controller;

import com.shared.info.SharedServicesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SharedServicesApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class HealthCheckControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_trigger_health_check_api_and_return_valid_response() throws Exception {
        var actualResponse = mockMvc.perform(MockMvcRequestBuilders.get("/health/health-check"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }
}
