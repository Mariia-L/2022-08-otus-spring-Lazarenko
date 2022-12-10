package ru.otus.test.service;

public interface TestProcessor {

    String startTest();
    int processTest();
    void printResult(String userName, int score);
}
