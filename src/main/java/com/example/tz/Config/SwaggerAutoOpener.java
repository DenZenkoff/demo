package com.example.tz.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.net.URI;

@Component
public class SwaggerAutoOpener implements ApplicationRunner {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(SwaggerAutoOpener.class);

    @Value("${server.port}")
    private int port;

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread.sleep(2000);

        String url = "http://localhost:" + port + swaggerPath;

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
                log.info("Swagger UI автоматически открыт по адресу: {}", url);
            } else {
                // Для Linux без GUI или для серверов
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("linux")) {
                    Runtime.getRuntime().exec(new String[]{"xdg-open", url});
                    log.info("Swagger UI открыт через xdg-open: {}", url);
                } else {
                    log.warn("Не удалось автоматически открыть браузер. Откройте вручную: {}", url);
                }
            }
        } catch (Exception e) {
            log.warn("Не удалось открыть браузер автоматически: {}", e.getMessage());
            log.info("Откройте Swagger UI вручную: {}", url);
        }
    }
}
