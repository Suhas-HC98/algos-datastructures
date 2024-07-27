package com.shared.info.service;

import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;

public interface PojoMapperService {

    ClientEntitlement customerEntitlementToClientEntitlement(CustomerEntitlements customerEntitlements);

    CustomerEntitlements clientEntitlementToCustomerEntitlement(ClientEntitlement clientEntitlement);
}
