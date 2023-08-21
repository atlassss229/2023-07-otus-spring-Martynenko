package ru.otus.service;

import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {


    private final Scanner scanner;

    private final PrintStream printStream;

    public IOServiceImpl() {
        this.scanner = new Scanner(System.in);
        this.printStream = System.out;
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void printLine(String line) {
        printStream.println(line);
    }
}

