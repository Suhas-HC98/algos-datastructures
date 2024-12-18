package com.shared.info.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntitlements {
    private String id;
    @NotNull(message = "domicileCountry must not be null")
    private String domicileCountry;
}
