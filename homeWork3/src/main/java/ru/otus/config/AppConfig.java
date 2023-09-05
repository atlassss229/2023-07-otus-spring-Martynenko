package ru.otus.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@EnableConfigurationProperties(AppProps.class)
@Configuration
public class AppConfig {
}
