package ru.otus.test.service;

import lombok.AllArgsConstructor;
import ru.otus.test.dao.TestDao;

@AllArgsConstructor
public class TestPrinterServiceImpl implements TestPrinterService {

    private TestReaderService testReaderService;

    @Override
    public void printTest() {

        for (TestDao test : testReaderService.getQuestions()) {

            System.out.println(test.getQuestion());
        }
    }
}
