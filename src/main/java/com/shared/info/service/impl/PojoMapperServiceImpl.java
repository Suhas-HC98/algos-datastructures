package com.shared.info.service.impl;

import com.shared.info.mapper.ClientCustomerMapper;
import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import com.shared.info.service.PojoMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PojoMapperServiceImpl implements PojoMapperService {

    private final ClientCustomerMapper mapper;

    @Override
    public ClientEntitlement customerEntitlementToClientEntitlement(CustomerEntitlements customerEntitlements) {
        return mapper.customerEntitlementToClientEntitlement(customerEntitlements);
    }

    @Override
    public CustomerEntitlements clientEntitlementToCustomerEntitlement(ClientEntitlement clientEntitlement) {
        return mapper.clientEntitlementToCustomerEntitlement(clientEntitlement);
    }
}
