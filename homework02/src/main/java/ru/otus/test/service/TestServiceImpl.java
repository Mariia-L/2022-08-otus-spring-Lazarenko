package ru.otus.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.test.domain.Student;
import ru.otus.test.domain.StudentResult;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final StudentService studentService;
    private final TestProcessor testProcessor;
    private final TestResultServiceImpl testResultService;

    @Override
    public void testUser() {

        Student student = studentService.loginStudent();
        StudentResult studentResult = testProcessor.processTest(student);

        if (studentResult != null) {

            testResultService.printResult(studentResult);
        }
    }
}
