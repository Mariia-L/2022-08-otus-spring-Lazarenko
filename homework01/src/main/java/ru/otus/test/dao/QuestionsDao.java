package ru.otus.test.dao;

import ru.otus.test.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionsDao {
    List<Question> getQuestions();
}
