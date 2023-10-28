package edu.nur.homework04.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource ms;

    private final Locale locale;

    public LocalizationServiceImpl(MessageSource ms, @Value("${quiz-app.locale}") Locale locale) {
        this.ms = ms;
        this.locale = locale;
    }

    @Override
    public String getMessage(String key) {
        return ms.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, Object[] args) {
        return ms.getMessage(key, args, locale);
    }

}
