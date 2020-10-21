package com.rocky.service;

import com.rocky.SpringBootDemoApplication;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Data
public class MessageImpl implements Message{

    public static final Logger LOG = LoggerFactory.getLogger(MessageImpl.class);

    private String message;

    @Override
    public String getMessage() {
        LOG.info("In getMessage Method");
        this.message = "Hello from SpringBoot Demo";
        LOG.info("Message Assigned successfully");
        return this.message;
    }
}
