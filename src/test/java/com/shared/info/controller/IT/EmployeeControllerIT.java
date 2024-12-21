package com.shared.info.controller.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shared.info.AutoConfigureTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static com.shared.info.utils.Constants.NON_PERSISTABLE;
import static com.shared.info.utils.Constants.PERSISTABLE;
import static com.shared.info.vo.TestUtils.BEARER_TOKEN;
import static com.shared.info.vo.TestUtils.employee;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wiremock.com.google.common.net.HttpHeaders.AUTHORIZATION;

@AutoConfigureTest
class EmployeeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_success_message_with_check1_when_proper_requestBody_is_passed() throws Exception {
        var response = mockMvc.perform(post("/emp/check-1")
                .header(AUTHORIZATION, BEARER_TOKEN)
                .content(objectMapper.writeValueAsString(employee()))
                .contentType(APPLICATION_JSON));

        response.andExpect(status().is2xxSuccessful());
        response.andExpect(jsonPath("$").exists());
        response.andExpect(jsonPath("$").value(PERSISTABLE));
    }

    @Test
    void should_return_validation_message_with_check1_when_proper_requestBody_is_passed() throws Exception {
        var emp = employee().toBuilder().empId(null).build();
        var response = mockMvc.perform(post("/emp/check-1")
                .header(AUTHORIZATION, BEARER_TOKEN)
                .content(objectMapper.writeValueAsString(emp))
                .contentType(APPLICATION_JSON));

        response.andExpect(status().isBadRequest());
        response.andExpect(jsonPath("$.empId").exists());
        response.andExpect(jsonPath("$.empName").doesNotHaveJsonPath());
        response.andExpect(jsonPath("$.empId").value("employee id is mandatory"));
    }

    @Test
    void should_return_success_message_with_check2_when_proper_requestBody_is_passed() throws Exception {
        var response = mockMvc.perform(post("/emp/check-2")
                .header(AUTHORIZATION, BEARER_TOKEN)
                .content(objectMapper.writeValueAsString(employee()))
                .contentType(APPLICATION_JSON));

        response.andExpect(status().is2xxSuccessful());
        response.andExpect(jsonPath("$").exists());
        response.andExpect(jsonPath("$").value(PERSISTABLE));
    }

    @Test
    void should_return_validation_message_with_check2_when_proper_requestBody_is_passed() throws Exception {
        var emp = employee().toBuilder().empId(null).build();
        var response = mockMvc.perform(post("/emp/check-2")
                .header(AUTHORIZATION, BEARER_TOKEN)
                .content(objectMapper.writeValueAsString(emp))
                .contentType(APPLICATION_JSON));

        response.andExpect(status().is2xxSuccessful());
        response.andExpect(jsonPath("$").value(NON_PERSISTABLE));
    }

    @Test
    void should_return_success_message_with_check3_when_proper_requestBody_is_passed() throws Exception {
        var response = mockMvc.perform(post("/emp/check-3")
                .header(AUTHORIZATION, BEARER_TOKEN)
                .content(objectMapper.writeValueAsString(employee()))
                .contentType(APPLICATION_JSON));

        response.andExpect(status().is2xxSuccessful());
        response.andExpect(jsonPath("$").exists());
        response.andExpect(jsonPath("$").value(PERSISTABLE));
    }

    @Test
    void should_return_validation_message_with_check3_when_proper_requestBody_is_passed() throws Exception {
        var emp = employee().toBuilder().empId(null).build();
        var response = mockMvc.perform(post("/emp/check-3")
                .header(AUTHORIZATION, BEARER_TOKEN)
                .content(objectMapper.writeValueAsString(emp))
                .contentType(APPLICATION_JSON));

        response.andExpect(status().is2xxSuccessful());
        response.andExpect(jsonPath("$").value(NON_PERSISTABLE));
    }
}