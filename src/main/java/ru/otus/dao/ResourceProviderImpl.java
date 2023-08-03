package ru.otus.dao;

import java.io.InputStream;

public class ResourceProviderImpl implements ResourceProvider {

    private final Resource resource;

    public ResourceProviderImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public InputStream getResource() {
        String file = resource.getResource();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(file);
    }
}
