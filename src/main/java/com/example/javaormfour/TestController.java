package com.example.javaormfour;

import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/total_info")
    public long getTotal() {
        //sql_count
        var output = repository.count();
        return output;
    }

    @GetMapping("/")
    public String getHome() {
        return "home page";
    }
}


