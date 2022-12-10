package ru.otus.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestProcessor testProcessor;

    @Override
    public void testUser() {

        String userName = testProcessor.startTest();
        int score = testProcessor.processTest();
        testProcessor.printResult(userName, score);
    }
}
