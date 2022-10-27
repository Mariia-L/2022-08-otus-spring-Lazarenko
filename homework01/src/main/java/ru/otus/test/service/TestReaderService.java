package ru.otus.test.service;

import ru.otus.test.dao.TestDao;

import java.util.List;

public interface TestReaderService {

    List<TestDao> getQuestions();
}
