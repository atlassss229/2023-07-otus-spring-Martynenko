package ru.otus.dao;

import ru.otus.model.Student;
import ru.otus.service.IOService;

public class StudentDaoImpl implements StudentDao {

    private final IOService ioService;

    public StudentDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student getStudent() {


        ioService.printLine("Enter your first name: ");
        String firstName = ioService.readLine();

        ioService.printLine("Enter your last name: ");
        String lastName = ioService.readLine();

        return new Student(firstName, lastName);
    }
}
