package com.example.tz.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;

@Component
@ConditionalOnProperty(name = "h2.console.auto-open", havingValue = "true", matchIfMissing = true)
public class H2AutoOpener implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(H2AutoOpener.class);

    @Value("${server.port}")
    private int port;

    @Value("${spring.h2.console.path:/h2-console}")
    private String h2Path;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread.sleep(2500);

        String url = "http://localhost:" + port + h2Path;

        log.info("H2 Console: {}", url);
    }
}
