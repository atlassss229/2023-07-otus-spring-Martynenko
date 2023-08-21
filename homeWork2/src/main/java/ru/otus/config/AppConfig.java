package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dao.ResourceProvider;
import ru.otus.dao.CsvQuestionDao;
import ru.otus.dao.StudentDaoImpl;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceImpl;
import ru.otus.service.RunServiceImpl;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig implements ResourceProvider {


    @Value("${csv.file.path}")
    private Resource resource;

    @Value("${question.required.toPass}")
    private Integer questionToPass;

    public Resource getResource() {
        return resource;
    }

    @Bean
    CsvQuestionDao csvQuestionDao() {
        return new CsvQuestionDao(getResource());
    }

    @Bean
    IOServiceImpl ioServce() {
        return new IOServiceImpl();
    }

    @Bean
    StudentDaoImpl studentDao (IOService ioService) {
        return new StudentDaoImpl(ioService);
    }

    @Bean
    RunServiceImpl runService (CsvQuestionDao csvQuestionDao, IOService ioService, StudentDaoImpl studentDao) {
        return new RunServiceImpl(csvQuestionDao, ioService, studentDao, questionToPass);
    }
}
