package ru.otus.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.AppProps;

import java.util.Locale;

@Service
@EnableConfigurationProperties(AppProps.class)
public class MessageSourceServiceImpl implements MessageSourceService {

    private final MessageSource messageSource;

    private final Locale locale;

    public MessageSourceServiceImpl(AppProps appProps, MessageSource messageSource) {
        this.locale = appProps.getLocale();
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String message, String[] args) {
        return messageSource.getMessage(message, args, locale);
    }
}
