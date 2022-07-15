package com.example.defmarket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DefMarketApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefMarketApplication.class);

    public static void main(String[] args) {
        ApplicationContext cx = SpringApplication.run(DefMarketApplication.class, args);
        LOGGER.info("LOGGER TEST");
    }
}
