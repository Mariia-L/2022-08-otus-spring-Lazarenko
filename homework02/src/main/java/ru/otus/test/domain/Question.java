package ru.otus.test.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {

    private long id;
    private String question;
    private String answer;
}
