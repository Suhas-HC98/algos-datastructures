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
@JsonPropertyOrder({"stdName", "stdId", "stdPhone"})
@JsonIgnoreProperties({"stdRollNumber"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Student {

    @JsonAlias({"studentId", "Id"})
    @NotNull(message = "student id is mandatory")
    private Integer stdId;

    @NotEmpty(message = "student name is mandatory")
    private String stdName;

    @Min(value = 100, message = "student phone must have at least 3 digits")
    @Max(value = 999999, message = "student phone must have at most 6 digits")
    private Long stdPhone;

    private String stdRollNumber;

    @JsonIgnore
    private int stdPoints;

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
