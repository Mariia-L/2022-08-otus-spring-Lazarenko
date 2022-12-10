package ru.otus.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class IOServiceStreams implements IOService {

    private final PrintStream output;
    private final Scanner input;

    @Override
    public void outputString(String s) {

        output.println(s);
    }

    @Override
    public String inputString() {

        return input.nextLine();
    }
}
