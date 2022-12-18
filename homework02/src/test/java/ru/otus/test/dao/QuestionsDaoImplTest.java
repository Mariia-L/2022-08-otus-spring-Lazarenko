package ru.otus.test.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.test.QuestionDaoException;
import ru.otus.test.domain.Question;

import java.util.List;

class QuestionsDaoImplTest {

    @Test
    public void testSuccess() throws QuestionDaoException {

        QuestionsDaoImpl questionsDao = new QuestionsDaoImpl("questions.csv");
        List<Question> questionList = questionsDao.getQuestions();
        Assertions.assertEquals(2, questionList.size());
        Assertions.assertEquals("First question?", questionList.get(0).getQuestion());
        Assertions.assertEquals("First question?", questionList.get(1).getQuestion());
        Assertions.assertEquals("Yes", questionList.get(0).getAnswer());
        Assertions.assertEquals("No", questionList.get(1).getAnswer());
    }

    @Test
    public void testBrokenFile() throws QuestionDaoException {

        QuestionsDaoImpl questionsDao = new QuestionsDaoImpl("questionsBrokenFile.csv");
        List<Question> questionList = questionsDao.getQuestions();
        Assertions.assertEquals(1, questionList.size());
    }

    @Test
    public void testNoFile() throws QuestionDaoException {

        QuestionsDaoImpl questionsDao = new QuestionsDaoImpl("111.csv");
        List<Question> questionList = questionsDao.getQuestions();
        Assertions.assertEquals(0, questionList.size());
    }
}