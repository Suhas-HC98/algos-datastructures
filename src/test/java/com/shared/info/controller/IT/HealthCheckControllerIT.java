package com.shared.info.controller.IT;

import com.shared.info.AutoConfigureTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.shared.info.vo.TestUtils.BEARER_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTest
class HealthCheckControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_trigger_health_check_api_and_return_valid_response() throws Exception {
        var actualResponse = mockMvc.perform(get("/health/health-check")
                        .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }
}
