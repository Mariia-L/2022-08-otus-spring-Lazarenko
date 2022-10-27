package ru.otus.test.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import ru.otus.test.dao.TestDao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class TestReaderServiceImpl implements TestReaderService {

    private String fileName;

    @Override
    public List<TestDao> getQuestions() {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null)
            return Collections.emptyList();

        InputStreamReader reader = new InputStreamReader(inputStream);

        List<TestDao> test = new CsvToBeanBuilder(reader)
                    .withType(TestDao.class)
                    .build()
                    .parse();

        return test;
    }
}
