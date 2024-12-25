package com.shared.info.cucumber;

import com.shared.info.utils.GenericResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealCheckStepDefs {

    @LocalServerPort
    String port;

    ResponseEntity<GenericResponse> lastResponse;

    @When("Health check API is triggered {string}")
    public void health_check_api_is_triggered(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("suhas", "suhas");
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, httpEntity,
                GenericResponse.class);
    }

    @When("status is {int}")
    public void status_is(Integer int1) {
        assertThat(lastResponse.getStatusCodeValue() == int1);
    }

    @Then("response is {string}")
    public void response_is(String string) {
        List<String> data = new ArrayList<>(lastResponse.getBody().data());
        assertEquals(string, data.get(0));
    }

}
