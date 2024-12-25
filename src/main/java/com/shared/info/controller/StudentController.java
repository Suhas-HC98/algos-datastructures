package com.shared.info.controller;

import com.shared.info.controller.documentation.StudentControllerDocumentation;
import com.shared.info.service.StudentService;
import com.shared.info.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalTime;

import static com.shared.info.utils.SharedServiceResponseBuilder.wrapWithGenericResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/std")
@RequiredArgsConstructor
public class StudentController implements StudentControllerDocumentation {

    private final StudentService service;
    private final Clock clock;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public GenericResponse<?> getStudentById(@PathVariable(name = "id") Integer stdId) {
        log.info("request received for fetching student details at {}", LocalTime.now(clock));
        return wrapWithGenericResponse(service.getByStudentId(stdId));
    }

    @GetMapping(value = "/name/{id}", produces = APPLICATION_JSON_VALUE)
    public GenericResponse<?> getStudentNameById(@PathVariable(name = "id") Integer stdId) {
        log.info("request received for fetching student name at {}", LocalTime.now(clock));
        return wrapWithGenericResponse(service.getStudentNameById(stdId));
    }

    @GetMapping(value = "/property/{id}", produces = APPLICATION_JSON_VALUE)
    public GenericResponse<?> getStudentPropertiesById(@PathVariable(name = "id") Integer stdId) {
        log.info("request received for fetching student's additional details at {}", LocalTime.now(clock));
        return wrapWithGenericResponse(service.getStudentPropertiesById(stdId));
    }
}
