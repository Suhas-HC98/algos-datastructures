package com.shared.info.mapper;

import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientCustomerMapper {

    ClientEntitlement customerEntitlementToClientEntitlement(CustomerEntitlements customerEntitlements);

    CustomerEntitlements clientEntitlementToCustomerEntitlement(ClientEntitlement clientEntitlement);
}
