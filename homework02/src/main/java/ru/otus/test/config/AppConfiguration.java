package ru.otus.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:settings.properties")
public class AppConfiguration {

    @Bean
    String questionFilePath(@Value("${questions.file.path}") String path) {

        return path;
    }

    @Bean
    PrintStream output() {

        return System.out;
    }

    @Bean
    Scanner input() {

        return new Scanner(System.in);
    }
}
