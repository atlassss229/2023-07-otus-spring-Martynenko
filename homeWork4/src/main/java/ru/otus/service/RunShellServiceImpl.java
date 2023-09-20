package ru.otus.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class RunShellServiceImpl {
    private final RunServiceImpl runService;

    public RunShellServiceImpl(RunServiceImpl runService) {
        this.runService = runService;
    }

    @ShellMethod(value = "start-test", key = {"st", "start-test"})
    public void startTesting() {
        runService.run();
    }
}
