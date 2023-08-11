package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.RunServiceImpl;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        RunServiceImpl runTestService = context.getBean(RunServiceImpl.class);
        runTestService.run();
    }
}
