package com.example.jfrsocketmicrometersample;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.binder.MeterBinder;
import jdk.jfr.consumer.RecordingStream;

@Configuration(proxyBeanMethods = false)
public class SocketObservingConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Bean
    public MeterBinder record() {
        return (registry) -> {
            var rs = new RecordingStream();

            rs.enable("jdk.SocketRead").withPeriod(Duration.ofMinutes(1));
            rs.enable("jdk.SocketWrite").withPeriod(Duration.ofMinutes(1));
            rs.enable("jdk.FileRead").withPeriod(Duration.ofMinutes(1));
            rs.enable("jdk.FileWrite").withPeriod(Duration.ofMinutes(1));

            rs.onEvent("jdk.SocketRead", event -> {
                var host = event.getString("host");
                var address = event.getString("address");
                var bytesRead = event.getLong("bytesRead");

                logger.info("Got {} host={} address={} bytesRead={}", event.getEventType().getName(),
                            host, address, bytesRead);
                registry.counter("jdk_socket_read_bytes", List.of(
                        new ImmutableTag("host", host),
                        new ImmutableTag("address", address)
                )).increment(bytesRead);
            });
            rs.onEvent("jdk.SocketWrite", event -> {
                var host = event.getString("host");
                var address = event.getString("address");
                var bytesWritten = event.getLong("bytesWritten");

                logger.info("Got {} host={} address={} bytesWritten={}", event.getEventType().getName(),
                            host, address, bytesWritten);
                registry.counter("jdk_socket_write_bytes", List.of(
                        new ImmutableTag("host", host),
                        new ImmutableTag("address", address)
                )).increment(bytesWritten);
            });
            rs.onEvent("jdk.FileRead", event -> {
                var path = event.getString("path");
                var bytesRead = event.getLong("bytesRead");
                if (path == null) {
                    path = "N/A";
                }

                logger.info("Got {} path={} bytesRead={}", event.getEventType().getName(),
                            path, bytesRead);
                registry.counter("jdk_file_read_bytes", List.of(
                        new ImmutableTag("path", path)
                )).increment(bytesRead);
            });
            rs.onEvent("jdk.FileWrite", event -> {
                var path = event.getString("path");
                var bytesWritten = event.getLong("bytesWritten");
                if (path == null) {
                    path = "N/A";
                }

                logger.info("Got {} path={} bytesWritten={}", event.getEventType().getName(),
                            path,
                            bytesWritten);
                registry.counter("jdk_file_write_bytes", List.of(
                        new ImmutableTag("path", path)
                )).increment(bytesWritten);
            });
            rs.startAsync();
        };
    }
}
