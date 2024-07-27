package com.shared.info.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldsCheckStepDefs {

    @LocalServerPort
    String port;

    ResponseEntity<?> lastResponse;

    @When("user makes API request {string}")
    public void makeRequest(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("suhas", "suhas");
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, httpEntity,
                Object.class);
    }

    @Then("response is good")
    public void checkStatus() {
        assertEquals(HttpStatus.OK, lastResponse.getStatusCode());
    }
}
