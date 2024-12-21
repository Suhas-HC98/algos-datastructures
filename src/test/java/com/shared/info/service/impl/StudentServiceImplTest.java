package com.shared.info.service.impl;

import com.shared.info.dto.Student;
import com.shared.info.service.StudentService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StudentServiceImplTest {

    private final StudentService service = new StudentServiceImpl();

    @ParameterizedTest(name = "Test scenario {index} - get student details by id")
    @MethodSource("students")
    void should_return_student_details_when_student_ids_are_passed(Student student) {
        var studentById = service.getByStudentId(student.getStdId());
        assertFalse(studentById.isEmpty());
        assertEquals(studentById.get(0).getStdId(), student.getStdId());
    }

    @ParameterizedTest(name = "Test scenario {index} - get student's name by id")
    @MethodSource("students")
    void should_return_student_name_when_student_ids_are_passed(Student student) {
        var studentNameById = service.getStudentNameById(student.getStdId());
        assertFalse(studentNameById.isEmpty());
        assertEquals(studentNameById.get(0), student.getStdName());
    }

    @ParameterizedTest(name = "Test scenario {index} - get student's additional properties by id")
    @MethodSource("students")
    void should_return_student_properties_when_student_ids_are_passed(Student student) {
        var studentPropertyById = service.getStudentPropertiesById(student.getStdId());
        assertFalse(studentPropertyById.isEmpty());
        assertEquals(studentPropertyById.get(0).get("computerSkills"),
                student.getAdditionalProperties().get("computerSkills"));
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