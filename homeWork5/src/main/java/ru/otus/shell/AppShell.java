package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.LibraryRunService;

@ShellComponent
@RequiredArgsConstructor
public class AppShell {

    private final LibraryRunService runService;

    @ShellMethod(value = "start", key = {"st", "start"})
    public void startTesting() {
        runService.run();
    }
}