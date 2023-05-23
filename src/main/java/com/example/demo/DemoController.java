package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(value = "/demo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String demo() {
        String fromProperty = System.getProperty("demo.hello");
        return fromProperty == null ? "demo" : fromProperty;
    }

}
