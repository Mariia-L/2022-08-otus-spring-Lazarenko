package ru.otus.test.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.otus.test.dao.QuestionsDao;
import ru.otus.test.domain.Question;
import ru.otus.test.domain.Student;
import ru.otus.test.domain.StudentResult;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestProcessorImpl implements TestProcessor {

    private final QuestionsDao questionsDao;
    private final IOService ioService;

    @Override
    public StudentResult processTest(Student student) {

        int score = 0;
        List<Question> questionList = questionsDao.getQuestions();

        if (CollectionUtils.isEmpty(questionList)) {

            ioService.outputString("No available questions. Please, contact administrator or try later");
            return null;
        }

        for (Question test : questionList) {

            ioService.outputString(test.getQuestion());
            String answer = ioService.inputString();

            if (test.getAnswer().equalsIgnoreCase(answer)){

                score++;
            }
        }

        return new StudentResult(student, score, questionList.size());
    }
}
