package ru.otus.test.dao;

import ru.otus.test.domain.Question;

import java.util.List;

public interface QuestionsDao {

    List<Question> getQuestions();
}
