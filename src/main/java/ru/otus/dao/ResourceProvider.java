package ru.otus.dao;


import java.io.InputStream;

public interface ResourceProvider {

    InputStream getResource();
}
