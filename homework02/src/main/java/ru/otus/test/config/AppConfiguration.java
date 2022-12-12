package ru.otus.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:settings.properties")
public class AppConfiguration {

    @Bean
    String questionFilePath(@Value("${questions.file.path}") String path) {

        return path;
    }
}
