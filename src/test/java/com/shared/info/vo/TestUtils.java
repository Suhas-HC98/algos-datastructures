package com.shared.info.vo;

import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.CustomerEntitlements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.UUID;

@Slf4j
public final class TestUtils {

    public String buildUrl(String port, String contextPath) {
        String url = "http://localhost:" + port + contextPath;
        log.info("URL : {}", url);
        return url;
    }

    public HttpHeaders httpHeaders(MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("suhas", "suhas");
        httpHeaders.setContentType(mediaType);

        return httpHeaders;
    }

    public static ClientEntitlement clientEntitlement(){
        return ClientEntitlement.builder().id(UUID.randomUUID().toString()).domicileCountry("CN").build();
    }

    public static CustomerEntitlements customerEntitlements(){
        return CustomerEntitlements.builder().id(UUID.randomUUID().toString()).domicileCountry("IN").build();
    }
}
