package com.rcoem.devops.interfaces;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class DevopsController {

    @GetMapping
    String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/test")
    String testServer(){
        return "Connected to Server 1";
    }

    @GetMapping("/{id}")
    String helloWorld(@PathVariable String id){
        return "Hello " + id + " !";
    }

    @PostMapping
    String addSomething(){
        return "Added Something";
    }
}
