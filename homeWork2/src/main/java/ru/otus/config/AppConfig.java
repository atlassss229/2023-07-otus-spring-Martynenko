package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dao.ResourceProviderImpl;
import ru.otus.service.RunServiceImpl;
import ru.otus.service.TestCreatorServiceImpl;

@PropertySource("classpath:application.properties")
@Configuration

public class AppConfig {

    @Value("${csv.file.path}")
    private Resource resource;

    @Value("${question.required.toPass}")
    private Integer questionToPass;

    @Bean
    ResourceProviderImpl resourceProvider() {
        return new ResourceProviderImpl(resource);
    }

    @Bean
    TestCreatorServiceImpl testCreatorService(ResourceProviderImpl resourceProvider) {
        return new TestCreatorServiceImpl(resourceProvider);
    }

    @Bean
    RunServiceImpl runService (TestCreatorServiceImpl testCreatorService) {
        return new RunServiceImpl(testCreatorService, questionToPass);
    }
}
