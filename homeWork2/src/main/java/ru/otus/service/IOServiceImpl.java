package ru.otus.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {
    @Override
    public String readLine() {

        String input = "SomeString";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        return new Scanner(System.in).nextLine();
    }

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    public int readInt() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                printLine("enter correct int value");
            }
        }
    }
}
