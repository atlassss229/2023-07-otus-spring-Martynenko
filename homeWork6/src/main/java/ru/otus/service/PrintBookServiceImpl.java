package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import ru.otus.model.Book;

@Service
@RequiredArgsConstructor
public class PrintBookServiceImpl implements PrintBookService {

    private final IOService ioService;

    @Override
    public void printBook(Book book) {
        ioService.printLine("\n----------");
        ioService.printLine(String.valueOf(book.getId()));
        ioService.printLine(book.getName());
        ioService.printLine(String.valueOf(book.getYear()));
        ioService.printLine(book.getAuthor().getName());
        ioService.printLine(book.getGenre().getName());
        ioService.printLine("----------");
    }
}
