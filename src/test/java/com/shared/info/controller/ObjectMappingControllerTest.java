package com.shared.info.controller;

import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import com.shared.info.service.PojoMapperService;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;

import static com.shared.info.vo.TestUtils.clientEntitlement;
import static com.shared.info.vo.TestUtils.customerEntitlements;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ObjectMappingControllerTest {

    private final PojoMapperService service = mock(PojoMapperService.class);
    private final ObjectMappingController controller = new ObjectMappingController(service);

    @Test
    void should_return_client_entitlements_mapped_object_when_customer_entitlement_object_is_passed() {
        var clientEntitlement = clientEntitlement();
        var customerEntitlement = customerEntitlements();
        var bindingResult = mock(BindingResult.class);
        when(service.customerEntitlementToClientEntitlement(customerEntitlement)).thenReturn(clientEntitlement);
        var response = controller.customerEntitlementToClientEntitlement(customerEntitlement, bindingResult);
        assertThat(response).isInstanceOf(ClientEntitlement.class);
        assertThat(response).isSameAs(clientEntitlement);
        verify(service).customerEntitlementToClientEntitlement(customerEntitlement);
    }

    @Test
    void should_return_customer_entitlements_mapped_object_when_client_entitlement_object_is_passed() {
        var clientEntitlement = clientEntitlement();
        var customerEntitlement = customerEntitlements();

        when(service.clientEntitlementToCustomerEntitlement(clientEntitlement)).thenReturn(customerEntitlement);
        var response = controller.clientEntitlementToCustomerEntitlement(clientEntitlement);
        assertThat(response).isInstanceOf(CustomerEntitlements.class);
        assertThat(response).isSameAs(customerEntitlement);
        verify(service).clientEntitlementToCustomerEntitlement(clientEntitlement);
    }

}