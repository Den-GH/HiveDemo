package com.example.hivedemo.config;

import com.hivemq.embedded.EmbeddedExtension;
import com.hivemq.embedded.EmbeddedHiveMQ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class HiveMQConfig {
    @Bean
    public EmbeddedHiveMQ embeddedHiveMQ() {
//        final Path configPath = Paths.get("./hivemq-config");
//        final Path dataPath = Paths.get("./hivemq-data");
//        final Path extensionsPath = Paths.get("./hivemq-extensions");

        final Path configPath = Paths.get("hivemq-config");
        final Path dataPath = Paths.get("hivemq-data");
        final Path extensionsPath = Paths.get("hivemq-extensions");

        final EmbeddedExtension embeddedExtension = EmbeddedExtension.builder()
                .withId("embedded-ext-1")
                .withName("Embedded Extension")
                .withVersion("1.0.0")
                .withPriority(0)
                .withStartPriority(1000)
                .withAuthor("Me")
                .withExtensionMain(new HiveMQExtension())
                .build();

        return EmbeddedHiveMQ.builder()
                .withConfigurationFolder(configPath)
                .withDataFolder(dataPath)
                .withExtensionsFolder(extensionsPath)
                .withEmbeddedExtension(embeddedExtension)
                .build();
    }
}
