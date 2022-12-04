package ru.otus.test.service;

import lombok.AllArgsConstructor;
import ru.otus.test.dao.QuestionsDao;
import ru.otus.test.domain.Question;

import java.io.IOException;

@AllArgsConstructor
public class TestPrinterServiceImpl implements TestPrinterService
{
    private final QuestionsDao testReaderService;
    private final IOService printStream;

    @Override
    public void printTest()
    {
        try
        {
            for (Question test : testReaderService.getQuestions())
            {
                printStream.outputString(test.getQuestion());
            }
        }
        catch (IOException e)
        {
            printStream.outputString("Error");
        }
    }
}
