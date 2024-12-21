package com.shared.info.controller.IT;

import com.shared.info.AutoConfigureTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.shared.info.vo.TestUtils.BEARER_TOKEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static wiremock.com.google.common.net.HttpHeaders.AUTHORIZATION;

@AutoConfigureTest
class StudentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_student_details_when_student_id_is_passed() throws Exception {
        mockMvc.perform(get("/std/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData").exists())
                .andExpect(jsonPath("$.metaData.isSuccess").value(true))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.isSuccess").value(true));
    }

    @Test
    void should_return_student_name_when_student_id_is_passed() throws Exception {
        mockMvc.perform(get("/std/name/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData").exists())
                .andExpect(jsonPath("$.metaData.isSuccess").value(true))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data").hasJsonPath())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.isSuccess").value(true));
    }

    @Test
    void should_return_students_additional_properties_when_student_id_is_passed() throws Exception {
        mockMvc.perform(get("/std/property/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, BEARER_TOKEN))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData").exists())
                .andExpect(jsonPath("$.metaData.isSuccess").value(true))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data").hasJsonPath())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.isSuccess").value(true));
    }
}