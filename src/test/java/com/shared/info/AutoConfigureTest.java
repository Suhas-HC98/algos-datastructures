package com.shared.info;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 8231)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@WithMockUser(username = "SUHAS", roles = {"ADMIN"})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoConfigureTest {
}
