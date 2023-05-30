package cn.edu.fudan.advweb.backend.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "world") String name) {
        return String.format("Hello, %s!", name);
    }

}
