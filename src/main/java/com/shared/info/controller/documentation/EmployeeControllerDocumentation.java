package com.shared.info.controller.documentation;

import com.shared.info.dto.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface EmployeeControllerDocumentation {

    @Operation(method = "POST", description = "Save Employee w.r.t Validation-1", tags = "emp")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    String checkAndPersist(@Valid Employee employee);

    @Operation(method = "POST", description = "Save Employee w.r.t Validation-2", tags = "emp")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    String bindingResultCheckAndPersist(@Valid Employee employee, BindingResult result);

    @Operation(method = "POST", description = "Save Employee w.r.t Validation-3", tags = "emp")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    String validatorCheckAndPersist(Employee employee);
}
