package com.korpusoff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(RestApplication.class, args);

        Communication communication = context.getBean("communication", Communication.class);
        System.out.println(communication.code());
    }
}
