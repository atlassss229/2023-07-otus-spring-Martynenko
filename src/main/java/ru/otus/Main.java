package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.RunServiceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        RunServiceImpl runTestService = context.getBean(RunServiceImpl.class);
        runTestService.run();
    }
}
