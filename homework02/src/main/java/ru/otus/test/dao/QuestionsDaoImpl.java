package ru.otus.test.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.test.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Repository
@AllArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    private final String questionFilePath;

    @Override
    public List<Question> getQuestions() {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(questionFilePath)) {

            if (inputStream == null) {
                return Collections.emptyList();
            }

            InputStreamReader reader = new InputStreamReader(inputStream);

            return new CsvToBeanBuilder<Question>(reader)
                    .withType(Question.class)
                    .build()
                    .parse();

        } catch (Exception exception) {

            return Collections.emptyList();
        }
    }
}
