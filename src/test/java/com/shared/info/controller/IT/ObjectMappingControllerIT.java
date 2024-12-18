package com.shared.info.controller.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shared.info.AutoConfigureTest;
import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.shared.info.vo.TestUtils.BEARER_TOKEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static wiremock.com.google.common.net.HttpHeaders.AUTHORIZATION;

@AutoConfigureTest
class ObjectMappingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_customer_entitlement_when_client_entitlement_is_passed() throws Exception {
        var clientEntitlement = ClientEntitlement.builder().id("clientId1").domicileCountry("IN").build();
        var resultActions = mockMvc.perform(post("/mapper/customer")
                .content(objectMapper.writeValueAsString(clientEntitlement))
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, BEARER_TOKEN));
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void should_return_client_entitlement_when_customer_entitlement_is_passed() throws Exception {
        var customerEntitlements = CustomerEntitlements.builder().id("clientId1").domicileCountry("IN").build();
        var resultActions = mockMvc.perform(post("/mapper/client")
                .content(objectMapper.writeValueAsString(customerEntitlements))
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, BEARER_TOKEN));
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void should_throw_MethodArgumentNotValidException_when_id_is_empty_while_mapping_request() throws Exception {
        var clientEntitlement = ClientEntitlement.builder().id("").domicileCountry("IN").build();
        mockMvc.perform(post("/mapper/customer")
                        .content(objectMapper.writeValueAsString(clientEntitlement))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value("id must not be empty"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}