package ru.otus.test.service;

import org.springframework.stereotype.Service;
import ru.otus.test.domain.Student;

@Service
public interface StudentService {

    Student loginStudent();
}
