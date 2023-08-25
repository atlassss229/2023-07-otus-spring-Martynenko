package ru.otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.core.io.Resource;
import ru.otus.dao.LocaleProvider;
import ru.otus.dao.ResourceProvider;
import ru.otus.dao.QuestionParamProvider;

import java.util.Locale;


@ConfigurationProperties(prefix = "application")
public class AppProps implements ResourceProvider, LocaleProvider, QuestionParamProvider {

    private final Resource resource;

    private final Integer questionToPass;

    private final Locale locale;

    @ConstructorBinding
    public AppProps(Resource resource, Integer questionToPass, Locale locale) {
        this.resource = resource;
        this.questionToPass = questionToPass;
        this.locale = locale;
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public Integer getQuestionToPass() {
        return questionToPass;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
