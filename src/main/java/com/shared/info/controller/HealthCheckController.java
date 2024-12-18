package com.shared.info.controller;

import com.shared.info.controller.documentation.HealthCheckControllerDocumentation;
import com.shared.info.utils.GenericResponse;
import com.shared.info.utils.SharedServiceResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/health")
public final class HealthCheckController implements HealthCheckControllerDocumentation {

    @GetMapping(value = "/health-check", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<?>> healthCheck() {
        return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithGenericResponse("Health Check isSuccess"), HttpStatus.OK);
    }
}
