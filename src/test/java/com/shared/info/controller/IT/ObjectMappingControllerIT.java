package com.shared.info.controller.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shared.info.AutoConfigureTest;
import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void should_return_client_entitlement_when_customer_entitlement_is_passed() throws Exception {
        var customerEntitlements = CustomerEntitlements.builder().id("clientId1").domicileCountry("IN").build();
        var resultActions = mockMvc.perform(post("/mapper/client")
                .content(objectMapper.writeValueAsString(customerEntitlements))
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}