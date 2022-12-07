package ru.otus.test.service;

import lombok.AllArgsConstructor;

import java.io.PrintStream;

@AllArgsConstructor
public class IOServiceStreams implements IOService {

    private final PrintStream output;

    @Override
    public void outputString(String s) {

        output.println(s);
    }
}
