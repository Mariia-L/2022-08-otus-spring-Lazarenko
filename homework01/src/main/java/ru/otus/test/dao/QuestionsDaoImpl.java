package ru.otus.test.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import ru.otus.test.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    private final String fileName;

    @Override
    public List<Question> getQuestions(){

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {

            if (inputStream == null) {
                return Collections.emptyList();
            }

            InputStreamReader reader = new InputStreamReader(inputStream);

            List<Question> test = new CsvToBeanBuilder(reader)
                    .withType(Question.class)
                    .build()
                    .parse();

            return test;
        } catch (Exception exception){

            return Collections.emptyList();
        }
    }
}
