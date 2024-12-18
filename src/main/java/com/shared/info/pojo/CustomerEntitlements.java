package com.shared.info.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntitlements {
    @Size(min = 3, max = 15, message = "id should be between 3 and 15 only")
    private String id;
    @NotNull(message = "com.shared.info.messages.domicileCountry")
    private String domicileCountry;
}
