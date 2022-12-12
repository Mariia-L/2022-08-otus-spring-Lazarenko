package ru.otus.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.test.domain.StudentResult;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService{

    private final IOService ioService;
    @Override
    public void printResult(StudentResult studentResult) {

        ioService.outputString(String.format("%s, your score is %d/%s",
                studentResult.getStudent().getName(), studentResult.getScore(), studentResult.getQuestionNumber()));
    }
}
