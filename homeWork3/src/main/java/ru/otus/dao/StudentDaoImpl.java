package ru.otus.dao;

import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.MessageSourceService;

public class StudentDaoImpl implements StudentDao {

    private final IOService ioService;

    private final MessageSourceService messageSourceService;

    public StudentDaoImpl(IOService ioService, MessageSourceService messageSourceService) {
        this.ioService = ioService;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public Student getStudent() {


        ioService.printLine(messageSourceService.getMessage("enter_first_name", null));
        String firstName = ioService.readLine();

        ioService.printLine(messageSourceService.getMessage("enter_last_name", null));
        String lastName = ioService.readLine();

        return new Student(firstName, lastName);
    }
}
