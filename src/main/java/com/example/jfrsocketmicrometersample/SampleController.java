package com.example.jfrsocketmicrometersample;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller to access the external site.
 * This controller required to demonstrate jdk.SocketRead/jdk.SocketWrite.
 */
@RestController
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/get_http")
    public String getHttp() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();
        var response = client.send(HttpRequest.newBuilder().GET().uri(URI.create("https://example.com/"))
                                              .build(), BodyHandlers.ofString());
        logger.info("Got response: {}", response.statusCode());
        return "http";
    }
}
