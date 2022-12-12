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
import ru.otus.test.domain.Student;
import ru.otus.test.domain.StudentResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class TestProcessorImplTest {

    private static final String STUDENT_NAME = "Name";
    private static final String QUESTION = "How are you?";
    private static final String OK_ANSWER = "Ok";

    @Mock
    private QuestionsDao questionsDao;

    @Mock
    private IOService ioService;

    @InjectMocks
    private TestProcessorImpl testProcessor;

    @Test
    public void testProcessTestRightAnswer() {

        List<Question> questionList = new ArrayList<>(2);
        Question question = new Question();
        question.setQuestion(QUESTION);
        question.setAnswer(OK_ANSWER);
        questionList.add(question);

        Mockito.when(questionsDao.getQuestions()).thenReturn(questionList);
        Mockito.when(ioService.inputString()).thenReturn(OK_ANSWER);

        StudentResult studentResult = testProcessor.processTest(new Student(STUDENT_NAME));

        Assertions.assertEquals(1, studentResult.getScore());
        Assertions.assertEquals(questionList.size(), studentResult.getQuestionNumber());
        Assertions.assertEquals(STUDENT_NAME, studentResult.getStudent().getName());
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

        StudentResult studentResult = testProcessor.processTest(new Student(STUDENT_NAME));

        Assertions.assertEquals(0, studentResult.getScore());
        Assertions.assertEquals(questionList.size(), studentResult.getQuestionNumber());
        Assertions.assertEquals(STUDENT_NAME, studentResult.getStudent().getName());
    }

    @Test
    public void testProcessTestNoQuestions() {

        Mockito.when(questionsDao.getQuestions()).thenReturn(Collections.emptyList());

        StudentResult studentResult = testProcessor.processTest(new Student(STUDENT_NAME));

        Mockito.verify(ioService, never()).inputString();
        Assertions.assertNull(studentResult);
    }
}