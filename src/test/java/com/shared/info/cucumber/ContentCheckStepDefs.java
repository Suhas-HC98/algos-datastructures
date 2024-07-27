package com.shared.info.cucumber;

import com.shared.info.utils.GenericResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContentCheckStepDefs {

    @LocalServerPort
    String port;

    ResponseEntity<GenericResponse> lastResponse;

    @Given("user makes API request {string} with params {string}")
    public void userMakesAPIRequestWithParams(String arg0, String arg1) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("suhas", "suhas");
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        System.out.println(arg1);
        String url = "http://localhost:" + port + arg0 + "?fileName=" + arg1;
        System.out.println(url);
        lastResponse = new RestTemplate().exchange(url,
                HttpMethod.GET, httpEntity,
                GenericResponse.class);
    }

    @And("response code is {int}")
    public void response_code_is(Integer int1) {
        assertThat(lastResponse.getStatusCodeValue()).isEqualTo(int1);
    }

    @Then("data produced is not null")
    public void data_produced_is_not_null() {
        assertNotNull(lastResponse.getBody().data());
    }
}
