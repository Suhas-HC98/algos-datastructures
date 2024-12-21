package com.shared.info.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientEntitlement {

    private String id;
    private String domicileCountry;
}
