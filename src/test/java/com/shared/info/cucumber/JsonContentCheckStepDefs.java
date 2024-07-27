package com.shared.info.cucumber;

import com.shared.info.utils.GenericResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonContentCheckStepDefs {

    @LocalServerPort
    String port;

    ResponseEntity<GenericResponse> apiResponse;

    @Given("User triggers {string} with following {string}")
    public void user_triggers_with_following(String string, String string2) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("suhas", "suhas");
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        String url = "http://localhost:" + port + string + "?stringPath=" + string2;
        System.out.println(url);
        apiResponse = new RestTemplate().exchange(url,
                HttpMethod.GET, httpEntity,
                GenericResponse.class);
    }

    @Then("Content is not empty")
    public void content_is_not_empty() {
        assertNotNull(apiResponse.getBody().data());
    }


}
