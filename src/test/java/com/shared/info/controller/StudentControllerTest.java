package com.shared.info.controller;

import com.shared.info.dto.Student;
import com.shared.info.service.StudentService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    private final StudentService service = mock(StudentService.class);
    private final Clock clock = Clock.fixed(Instant.parse("2024-12-21T10:15:30.00Z"), ZoneId.of("UTC"));
    private final StudentController controller = new StudentController(service, clock);


    @ParameterizedTest(name = "Test scenario {index} - get student details by id")
    @MethodSource("students")
    void should_return_student_details_when_student_ids_are_passed(Student student) {
        when(service.getByStudentId(student.getStdId())).thenReturn(List.of(student));
        var studentById = controller.getStudentById(student.getStdId());
        assertTrue(studentById.errors().isSuccess());
        assertTrue(studentById.metaData().isSuccess());
        assertEquals(1, studentById.data().size());
        verify(service).getByStudentId(student.getStdId());
        verify(service, never()).getStudentNameById(student.getStdId());
        verify(service, never()).getStudentPropertiesById(student.getStdId());
    }

    @ParameterizedTest(name = "Test scenario {index} - get student's name by id")
    @MethodSource("students")
    void should_return_student_name_when_student_ids_are_passed(Student student) {
        when(service.getStudentNameById(student.getStdId())).thenReturn(List.of(student.getStdName()));
        var studentById = controller.getStudentNameById(student.getStdId());
        assertTrue(studentById.errors().isSuccess());
        assertTrue(studentById.metaData().isSuccess());
        assertEquals(1, studentById.data().size());
        verify(service).getStudentNameById(student.getStdId());
        verify(service, never()).getByStudentId(student.getStdId());
        verify(service, never()).getStudentPropertiesById(student.getStdId());
    }

    @ParameterizedTest(name = "Test scenario {index} - get student's additional properties by id")
    @MethodSource("students")
    void should_return_student_properties_when_student_ids_are_passed(Student student) {
        when(service.getStudentPropertiesById(student.getStdId())).thenReturn(List.of(student.getAdditionalProperties()));
        var studentById = controller.getStudentPropertiesById(student.getStdId());
        assertTrue(studentById.errors().isSuccess());
        assertTrue(studentById.metaData().isSuccess());
        assertEquals(1, studentById.data().size());
        verify(service).getStudentPropertiesById(student.getStdId());
        verify(service, never()).getStudentNameById(student.getStdId());
        verify(service, never()).getByStudentId(student.getStdId());
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