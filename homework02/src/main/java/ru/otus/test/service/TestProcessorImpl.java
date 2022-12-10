package ru.otus.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.test.dao.QuestionsDao;
import ru.otus.test.domain.Question;

@Service
@RequiredArgsConstructor
public class TestProcessorImpl implements TestProcessor {

    private final QuestionsDao questionsDao;
    private final IOService ioService;

    @Override
    public String startTest() {

        ioService.outputString("Ваше имя и фамилия");
        return ioService.inputString();
    }

    @Override
    public int processTest() {

        int score = 0;

        for (Question test : questionsDao.getQuestions()) {

            ioService.outputString(test.getQuestion());
            String answer = ioService.inputString();

            if (test.getAnswer().equalsIgnoreCase(answer)){

                score++;
            }
        }

        return score;
    }

    @Override
    public void printResult(String userName, int score) {

        ioService.outputString(String.format("%s, ваш результат - %d %s", userName, score, getScoreWord(score)));
    }

    private String getScoreWord(int score) {

        if (score == 1) {
            return "балл";
        } else if (score > 1 && score < 5) {
            return "балла";
        }

        return "баллов";
    }
}
