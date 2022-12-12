package ru.otus.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.test.domain.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final IOService ioService;

    @Override
    public Student loginStudent() {

        ioService.outputString("Your first name and second name");
        return new Student(ioService.inputString());
    }
}
