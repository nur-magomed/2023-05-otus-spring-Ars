package edu.nur.homework03.service;

import edu.nur.homework03.config.AppProps;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource ms;

    private final AppProps appProps;

    public LocalizationServiceImpl(MessageSource ms, AppProps appProps) {
        this.ms = ms;
        this.appProps = appProps;
    }

    @Override
    public String getMessage(String key) {
        return ms.getMessage(key, null, appProps.getLocale());
    }

    @Override
    public String getMessage(String key, Object[] args) {
        return ms.getMessage(key, args, appProps.getLocale());
    }

}
