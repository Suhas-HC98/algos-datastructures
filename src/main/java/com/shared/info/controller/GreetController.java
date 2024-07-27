package com.shared.info.controller;

import com.shared.info.controller.documentation.GreetControllerDocumentation;
import com.shared.info.service.GreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/graphql")
@RequiredArgsConstructor
public class GreetController implements GreetControllerDocumentation {

    private final GreetService service;

    @GetMapping(value = "/greet")
    public String greet(String name) {
        return service.greet(name);
    }
}
