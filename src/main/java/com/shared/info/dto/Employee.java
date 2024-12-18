package com.shared.info.dto;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonPropertyOrder({"empId", "empLocation", "empPhone", "empName"})
@JsonIgnoreProperties({"designation"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Employee {

    @JsonAlias({"employeeId", "Id"})
    @NotNull(message = "employee id is mandatory")
    private Integer empId;

    @NotEmpty(message = "employee name is mandatory")
    private String empName;

    @NotEmpty(message = "employee location is mandatory")
    private String empLocation;

    @NotNull(message = "employee phone is mandatory")
    @Min(value = 100, message = "employee phone must have at least 3 digits")
    @Max(value = 999999, message = "employee phone must have at most 6 digits")
    private Long empPhone;

    private String designation;

    @JsonIgnore
    private int points;

    private Map<String, String> additionalProperties;

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public void addAdditionalProperties(String key, String value) {
        this.additionalProperties.put(key, value);
    }
}
