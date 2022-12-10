package ru.otus.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.test.dao.QuestionsDao;
import ru.otus.test.domain.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TestProcessorImplTest {

    private static final String USER_NAME = "Name";
    private static final String QUESTION = "How are you?";
    private static final String OK_ANSWER = "Ok";
    private static final String RESULT_PATTERN = "%s, ваш результат - %d %s";

    @Mock
    private QuestionsDao questionsDao;

    @Mock
    private IOService ioService;

    @InjectMocks
    private TestProcessorImpl testProcessor;

    @Test
    public void testStartTest() {

        Mockito.when(ioService.inputString()).thenReturn(USER_NAME);

        String userName = testProcessor.startTest();

        Mockito.verify(ioService, times(1)).outputString("Ваше имя и фамилия");
        Assertions.assertEquals(USER_NAME, userName);
    }

    @Test
    public void testProcessTestRightAnswer() {

        List<Question> questionList = new ArrayList<>(2);
        Question question1 = new Question();
        question1.setQuestion(QUESTION);
        question1.setAnswer(OK_ANSWER);
        questionList.add(question1);

        Mockito.when(questionsDao.getQuestions()).thenReturn(questionList);
        Mockito.when(ioService.inputString()).thenReturn(OK_ANSWER);

        int score = testProcessor.processTest();

        Assertions.assertEquals(1, score);
    }

    @Test
    public void testProcessTestWrongAnswer() {

        List<Question> questionList = new ArrayList<>(2);
        Question question1 = new Question();
        question1.setQuestion(QUESTION);
        question1.setAnswer(OK_ANSWER);
        questionList.add(question1);

        Mockito.when(questionsDao.getQuestions()).thenReturn(questionList);
        Mockito.when(ioService.inputString()).thenReturn("Well");

        int score = testProcessor.processTest();

        Assertions.assertEquals(0, score);
    }

    @Test
    public void testProcessTestNoQuestions() {

        Mockito.when(questionsDao.getQuestions()).thenReturn(Collections.emptyList());

        int score = testProcessor.processTest();

        Mockito.verify(ioService, never()).inputString();
        Assertions.assertEquals(0, score);
    }

    @Test
    public void testPrintResultZeroScore() {

        int score = 0;

        testProcessor.printResult(USER_NAME, score);

        Mockito.verify(ioService, times(1))
                .outputString(String.format(RESULT_PATTERN, USER_NAME, score, "баллов"));
    }

    @Test
    public void testPrintResultOneScore() {

        int score = 1;

        testProcessor.printResult(USER_NAME, score);

        Mockito.verify(ioService, times(1))
                .outputString(String.format(RESULT_PATTERN, USER_NAME, score, "балл"));
    }

    @Test
    public void testPrintResultMediumScore() {

        int score = 3;

        testProcessor.printResult(USER_NAME, score);

        Mockito.verify(ioService, times(1))
                .outputString(String.format(RESULT_PATTERN, USER_NAME, score, "балла"));
    }

    @Test
    public void testPrintResultHighScore() {

        int score = 5;

        testProcessor.printResult(USER_NAME, score);

        Mockito.verify(ioService, times(1))
                .outputString(String.format(RESULT_PATTERN, USER_NAME, score, "баллов"));
    }
}