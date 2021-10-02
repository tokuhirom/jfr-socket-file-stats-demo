package com.example.jfrsocketmicrometersample;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This controller required to demonstrate metrics.
 */
@Component
public class SampleScheduler {
    private static final Logger logger = LoggerFactory.getLogger(SampleScheduler.class);

    @Scheduled(fixedRate = 5000)
    public void getHttp() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();
        var response = client.send(HttpRequest.newBuilder().GET().uri(URI.create("https://example.com/"))
                                              .build(), BodyHandlers.ofString());
        logger.info("Got response: {}", response.statusCode());
    }

    @Scheduled(fixedRate = 5000)
    public void writeTempFile() throws IOException, InterruptedException {
        var tempFile = Files.createTempFile("a", "b");
        Files.writeString(tempFile, "Hello");
        logger.info("Write temp file: {}", tempFile);
    }
}
