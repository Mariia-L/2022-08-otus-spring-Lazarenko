package ru.otus.test.service;

import ru.otus.test.domain.Student;
import ru.otus.test.domain.StudentResult;

public interface TestProcessor {

    StudentResult processTest(Student student);
}
