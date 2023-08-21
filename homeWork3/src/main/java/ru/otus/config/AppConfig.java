package ru.otus.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.context.annotation.Configuration;
import ru.otus.dao.ResourceProvider;
import ru.otus.dao.CsvQuestionDao;
import ru.otus.dao.StudentDaoImpl;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceImpl;
import ru.otus.service.MessageSourceServiceImpl;
import ru.otus.service.RunServiceImpl;

import java.util.Locale;

@EnableConfigurationProperties(AppProps.class)
@Configuration
public class AppConfig implements ResourceProvider {

    private final AppProps appProps;

    public AppConfig(AppProps appProps) {
        this.appProps = appProps;
    }

    public Locale getLocale() {
        return appProps.getLocale();
    }

    public Integer questionToPass() {
        return appProps.getQuestionToPass();
    }

    public Resource getResource() {
        return appProps.getResource();
    }

    @Bean
    CsvQuestionDao csvQuestionDao() {
        return new CsvQuestionDao(getResource());
    }

    @Bean
    IOServiceImpl ioService() {
        return new IOServiceImpl();
    }

    @Bean
    MessageSourceServiceImpl messageSourceService (MessageSource messageSource) {
        return new MessageSourceServiceImpl(getLocale(), messageSource);
    }

    @Bean
    StudentDaoImpl studentDao (IOService ioService, MessageSourceServiceImpl messageSourceService) {
        return new StudentDaoImpl(ioService, messageSourceService);
    }

    @Bean
    RunServiceImpl runService (CsvQuestionDao csvQuestionDao,
                               IOService ioService,
                               StudentDaoImpl studentDao,
                               MessageSourceServiceImpl messageSourceService) {
        return new RunServiceImpl(
                csvQuestionDao,
                ioService,
                studentDao,
                questionToPass(),
                messageSourceService);
    }
}
