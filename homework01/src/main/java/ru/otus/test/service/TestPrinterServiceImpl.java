package ru.otus.test.service;

import lombok.AllArgsConstructor;
import ru.otus.test.dao.QuestionsDao;
import ru.otus.test.domain.Question;

@AllArgsConstructor
public class TestPrinterServiceImpl implements TestPrinterService {

    private final QuestionsDao questionsDao;
    private final IOService ioService;

    @Override
    public void printTest() {

        for (Question test : questionsDao.getQuestions()) {

            ioService.outputString(test.getQuestion());
        }
    }
}
