package ru.otus.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.otus.test.dao.QuestionsDao;
import ru.otus.test.domain.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TestPrinterServiceImplTest {

    private static final String QUESTION = "How are you?";

    @Mock
    private QuestionsDao questionsDao;

    @Mock
    private IOService ioService;

    @InjectMocks
    private TestPrinterServiceImpl testPrinterService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void printEmpty() {

        Mockito.when(questionsDao.getQuestions()).thenReturn(Collections.emptyList());
        testPrinterService.printTest();
        Mockito.verify(ioService, Mockito.never()).outputString(Mockito.any());
    }

    @Test
    public void printList(){

        List<Question> tests = new ArrayList<>(2);
        Question test = new Question();
        test.setId(1);
        test.setQuestion(QUESTION);

        tests.add(test);

        Mockito.when(questionsDao.getQuestions()).thenReturn(tests);
        testPrinterService.printTest();
        Mockito.verify(ioService).outputString(QUESTION);
    }
}