package ru.otus.test.service;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class IOServiceImpl implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceImpl()
    {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public void outputString(String s) {

        output.println(s);
    }

    @Override
    public String inputString() {

        return input.nextLine();
    }
}
