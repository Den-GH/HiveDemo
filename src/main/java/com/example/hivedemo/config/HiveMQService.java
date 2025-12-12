package com.example.hivedemo.config;

import com.hivemq.embedded.EmbeddedHiveMQ;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HiveMQService {
    private static final Logger logger = LoggerFactory.getLogger(HiveMQService.class);

    @Autowired
    private EmbeddedHiveMQ embeddedHiveMQ;

    @PostConstruct
    public void startHiveMQ() {
        try {
            embeddedHiveMQ.start().get(30, TimeUnit.SECONDS);
            logger.info("HiveMQ Embedded Broker started successfully on port 1883");
            logger.info("Web Interface available at http://localhost:8080");
        } catch (Exception e) {
            logger.error("Failed to start HiveMQ Embedded Broker", e);
            throw new RuntimeException("Failed to start HiveMQ", e);
        }
    }

    @PreDestroy
    public void stopHiveMQ() {
        try {
            if (embeddedHiveMQ != null) {
                embeddedHiveMQ.stop().get(30, TimeUnit.SECONDS);
                logger.info("HiveMQ Embedded Broker stopped successfully");
            }
        } catch (Exception e) {
            logger.error("Error stopping HiveMQ Embedded Broker", e);
        }
    }

//    public boolean isRunning() {
//        return embeddedHiveMQ != null && embeddedHiveMQ.getStatus().isRunning();
//    }
}
