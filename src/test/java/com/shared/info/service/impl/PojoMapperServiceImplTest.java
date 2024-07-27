package com.shared.info.service.impl;

import com.shared.info.mapper.ClientCustomerMapper;
import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import com.shared.info.service.PojoMapperService;
import org.junit.jupiter.api.Test;

import static com.shared.info.vo.TestUtils.clientEntitlement;
import static com.shared.info.vo.TestUtils.customerEntitlements;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PojoMapperServiceImplTest {

    private final ClientCustomerMapper mapper = mock(ClientCustomerMapper.class);
    private final PojoMapperService service = new PojoMapperServiceImpl(mapper);

    @Test
    void should_return_client_entitlements_mapped_object_when_customer_entitlement_object_is_passed() {
        var clientEntitlement = clientEntitlement();
        var customerEntitlement = customerEntitlements();

        when(mapper.customerEntitlementToClientEntitlement(customerEntitlement)).thenReturn(clientEntitlement);
        var response = service.customerEntitlementToClientEntitlement(customerEntitlement);
        assertThat(response).isInstanceOf(ClientEntitlement.class);
        assertThat(response).isSameAs(clientEntitlement);
        verify(mapper).customerEntitlementToClientEntitlement(customerEntitlement);
    }

    @Test
    void should_return_customer_entitlements_mapped_object_when_client_entitlement_object_is_passed() {
        var clientEntitlement = clientEntitlement();
        var customerEntitlement = customerEntitlements();

        when(mapper.clientEntitlementToCustomerEntitlement(clientEntitlement)).thenReturn(customerEntitlement);
        var response = service.clientEntitlementToCustomerEntitlement(clientEntitlement);
        assertThat(response).isInstanceOf(CustomerEntitlements.class);
        assertThat(response).isSameAs(customerEntitlement);
        verify(mapper).clientEntitlementToCustomerEntitlement(clientEntitlement);
    }
}