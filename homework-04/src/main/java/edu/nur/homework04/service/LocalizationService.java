package edu.nur.homework04.service;

public interface LocalizationService {

    String getMessage(String key);

    String getMessage(String key, Object[] args);

}
