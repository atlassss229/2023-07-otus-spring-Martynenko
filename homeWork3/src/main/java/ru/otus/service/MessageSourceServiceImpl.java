package ru.otus.service;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageSourceServiceImpl implements MessageSourceService {

    private final MessageSource messageSource;

    private final Locale locale;

    public MessageSourceServiceImpl(Locale locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String message, String[] args) {
        return messageSource.getMessage(message, args, locale);
    }
}
