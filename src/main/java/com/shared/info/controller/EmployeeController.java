package com.shared.info.controller;

import com.shared.info.controller.documentation.EmployeeControllerDocumentation;
import com.shared.info.dto.Employee;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.shared.info.utils.Constants.NON_PERSISTABLE;
import static com.shared.info.utils.Constants.PERSISTABLE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/emp")
@AllArgsConstructor
public class EmployeeController implements EmployeeControllerDocumentation {

    private Validator validator;

    @PostMapping(value = "/check-1", consumes = APPLICATION_JSON_VALUE)
    public String checkAndPersist(@Valid @RequestBody Employee employee) {
        return PERSISTABLE;
    }

    @PostMapping(value = "/check-2", consumes = APPLICATION_JSON_VALUE)
    public String bindingResultCheckAndPersist(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return NON_PERSISTABLE;
        }
        return PERSISTABLE;
    }

    @PostMapping(value = "/check-3", consumes = APPLICATION_JSON_VALUE)
    public String validatorCheckAndPersist(@RequestBody Employee employee) {
        Errors errors = validator.validateObject(employee);
        if (errors.hasErrors()) {
            return NON_PERSISTABLE;
        }
        return PERSISTABLE;
    }
}
