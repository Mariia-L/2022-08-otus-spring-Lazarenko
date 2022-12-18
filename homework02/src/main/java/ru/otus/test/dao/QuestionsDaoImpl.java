package ru.otus.test.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.test.QuestionDaoException;
import ru.otus.test.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Repository
public class QuestionsDaoImpl implements QuestionsDao {

    private final String questionFilePath;

    public QuestionsDaoImpl(@Value("${questions.file.path}") String questionFilePath)
    {
        this.questionFilePath = questionFilePath;
    }

    @Override
    public List<Question> getQuestions() throws QuestionDaoException {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(questionFilePath)) {

            if (inputStream == null) {
                return Collections.emptyList();
            }

            InputStreamReader reader = new InputStreamReader(inputStream);

            return new CsvToBeanBuilder<Question>(reader)
                    .withThrowExceptions(false)
                    .withType(Question.class)
                    .build()
                    .parse();

        } catch (Exception exception) {

            throw new QuestionDaoException();
        }
    }
}
