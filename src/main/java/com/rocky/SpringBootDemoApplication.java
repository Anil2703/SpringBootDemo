package com.rocky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static final Logger LOG = LoggerFactory.getLogger(SpringBootDemoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);

        LOG.info("SpringBootDemo Application is Running and ready for Requests...");
    }
}
