package ru.otus.dao;

public class ResourceImpl implements Resource {

    private final String file;

    public ResourceImpl(String file) {
        this.file = file;
    }

    @Override
    public String getResource() {
        return file;
    }
}
