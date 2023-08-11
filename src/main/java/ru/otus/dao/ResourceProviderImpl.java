package ru.otus.dao;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ResourceProviderImpl implements ResourceProvider {

    private final Resource resource;

    public ResourceProviderImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    public static class ResourceImpl implements Resource {

        private final String fileName;

        public ResourceImpl(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String getFile() {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            StringBuilder file = new StringBuilder();
            try (InputStream stream = classloader.getResourceAsStream(fileName)) {
                if (stream != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    while (reader.ready()) {
                        file.append(reader.readLine()).append("\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("got IOException");
            }

            return file.toString();
        }
    }
}
