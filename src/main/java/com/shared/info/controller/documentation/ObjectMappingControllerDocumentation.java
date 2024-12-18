package com.shared.info.controller.documentation;

import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.BindingResult;

public interface ObjectMappingControllerDocumentation {

    @Operation(method = "POST", description = "Convert Client to Customer Entitlement", tags = "mapper")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    CustomerEntitlements clientEntitlementToCustomerEntitlement(@Parameter(description = "request payload") ClientEntitlement clientEntitlement);

    @Operation(method = "POST", description = "Convert Customer to Client Entitlement", tags = "mapper")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ClientEntitlement customerEntitlementToClientEntitlement(@Parameter(description = "request payload") CustomerEntitlements customerEntitlements, BindingResult result);
}
