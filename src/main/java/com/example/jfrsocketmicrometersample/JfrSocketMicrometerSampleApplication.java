package com.example.jfrsocketmicrometersample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JfrSocketMicrometerSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JfrSocketMicrometerSampleApplication.class, args);
    }

}
