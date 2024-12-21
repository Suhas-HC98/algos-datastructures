package com.shared.info.controller.IT;

import com.shared.info.AutoConfigureTest;
import com.shared.info.dto.Student;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.stream.Stream;

import static com.shared.info.vo.TestUtils.BEARER_TOKEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static wiremock.com.google.common.net.HttpHeaders.AUTHORIZATION;

@AutoConfigureTest
class StudentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "Test scenario {index} - get student details by id")
    @MethodSource("students")
    void should_return_student_details_when_student_id_is_passed(Student student) throws Exception {
        mockMvc.perform(get("/std/" + student.getStdId())
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

    @ParameterizedTest(name = "Test scenario {index} - get student's name by id")
    @MethodSource("students")
    void should_return_student_name_when_student_id_is_passed(Student student) throws Exception {
        mockMvc.perform(get("/std/name/" + student.getStdId())
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

    @ParameterizedTest(name = "Test scenario {index} - get student's additional property by id")
    @MethodSource("students")
    void should_return_students_additional_properties_when_student_id_is_passed(Student student) throws Exception {
        mockMvc.perform(get("/std/property/" + student.getStdId())
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

    private static Stream<Arguments> students() {
        return Stream.of(
                Arguments.of(Student.builder()
                        .stdId(1)
                        .stdName("A")
                        .stdPhone(1L)
                        .stdPoints(1)
                        .stdRollNumber("101")
                        .additionalProperties(Map.of("computerSkills", "Y",
                                "sports", "N"))
                        .build()),
                Arguments.of(Student.builder()
                        .stdId(2)
                        .stdName("B")
                        .stdPhone(2L)
                        .stdPoints(2)
                        .stdRollNumber("102")
                        .additionalProperties(Map.of("computerSkills", "N",
                                "sports", "Y"))
                        .build()),
                Arguments.of(Student.builder()
                        .stdId(3)
                        .stdName("C")
                        .stdPhone(3L)
                        .stdPoints(3)
                        .stdRollNumber("103")
                        .additionalProperties(Map.of("computerSkills", "Y",
                                "sports", "Y"))
                        .build())
        );
    }
}