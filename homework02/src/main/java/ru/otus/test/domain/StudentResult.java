package ru.otus.test.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class StudentResult {

    private final Student student;
    private final int score;
    private final int questionNumber;
}
