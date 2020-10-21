package com.rocky.controller;

import com.rocky.SpringBootDemoApplication;
import com.rocky.service.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    public static final Logger LOG = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private Message message;

    //endpoint : http://localhost:9090/springbootdemo/hello
    @GetMapping("/hello")
    public String sayHello() {
        LOG.info("In RestController");
    return message.getMessage();

    }
}
