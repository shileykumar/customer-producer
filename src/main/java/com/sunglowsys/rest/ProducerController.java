package com.sunglowsys.rest;

import com.sunglowsys.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProducerController {
    private static final Logger log = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private MessageChannel output;

    @PostMapping("/publish")
    public Customer publishEvent(@RequestBody Customer customer) {
        log.info("Customer published : {}", customer);
        output.send(MessageBuilder.withPayload(customer).build());
        return customer;
    }
}
