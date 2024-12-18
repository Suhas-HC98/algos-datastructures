package com.shared.info.controller;

import com.shared.info.controller.documentation.EmployeeControllerDocumentation;
import com.shared.info.dto.Employee;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/emp")
@AllArgsConstructor
public class EmployeeController implements EmployeeControllerDocumentation {

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE)
    public String save(@Valid @RequestBody Employee employee) {
        return "Provided request body is valid for persistence";
    }
}
