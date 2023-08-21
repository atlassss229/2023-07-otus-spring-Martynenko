package ru.otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.core.io.Resource;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private final Resource resource;

    private final Integer questionToPass;

    private final Locale locale;

    @ConstructorBinding
    public AppProps(Resource resource, Integer questionToPass, Locale locale) {
        this.resource = resource;
        this.questionToPass = questionToPass;
        this.locale = locale;
    }

    public Resource getResource() {
        return resource;
    }

    public Integer getQuestionToPass() {
        return questionToPass;
    }

    public Locale getLocale() {
        return locale;
    }
}
