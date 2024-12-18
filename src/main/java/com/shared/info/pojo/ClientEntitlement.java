package com.shared.info.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientEntitlement {

    @NotEmpty(message = "id must not be empty")
    private String id;
    private String domicileCountry;
}
