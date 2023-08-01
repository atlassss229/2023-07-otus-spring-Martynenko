package ru.otus.dao;

import java.io.InputStream;

public class FileAsResourseDaoImpl implements FileAsResourseDao {

    private final String file;

    public FileAsResourseDaoImpl(String file) {
        this.file = file;
    }

    @Override
    public InputStream getDataFromFile() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(file);
    }
}
