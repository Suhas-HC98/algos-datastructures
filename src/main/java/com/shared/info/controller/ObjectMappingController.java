package com.shared.info.controller;

import com.shared.info.controller.documentation.ObjectMappingControllerDocumentation;
import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import com.shared.info.service.PojoMapperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mapper")
@AllArgsConstructor
public final class ObjectMappingController implements ObjectMappingControllerDocumentation {

    private final PojoMapperService service;

    @PostMapping(value = "/customer")
    public CustomerEntitlements clientEntitlementToCustomerEntitlement(@RequestBody ClientEntitlement clientEntitlement) {
        return service.clientEntitlementToCustomerEntitlement(clientEntitlement);
    }

    @PostMapping(value = "/client")
    public ClientEntitlement customerEntitlementToClientEntitlement(@RequestBody CustomerEntitlements customerEntitlements) {
        return service.customerEntitlementToClientEntitlement(customerEntitlements);
    }
}
