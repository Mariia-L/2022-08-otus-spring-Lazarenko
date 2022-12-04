package ru.otus.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.test.dao.QuestionsDao;
import ru.otus.test.dao.QuestionsDaoImpl;
import ru.otus.test.domain.Question;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestPrinterServiceImplTest
{
    private static final String QUESTION = "How are you?";

    QuestionsDao readerService;
    private ByteArrayOutputStream outputStreamCaptor;
    private IOService printStream;

    @BeforeEach
    public void setUp()
    {
        readerService = Mockito.mock(QuestionsDaoImpl.class);
        outputStreamCaptor = new ByteArrayOutputStream();
        printStream = new IOServiceStreams(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printEmpty()
    {
        TestPrinterService printerService = new TestPrinterServiceImpl(readerService, printStream);
        printerService.printTest();

        assertEquals("", outputStreamCaptor.toString());
    }

    @Test
    public void printList() throws IOException
    {
        List<Question> tests = new ArrayList<>(2);
        Question test = new Question();
        test.setId(1);
        test.setQuestion(QUESTION);

        tests.add(test);

        Mockito.when(readerService.getQuestions()).thenReturn(tests);

        TestPrinterService printerService = new TestPrinterServiceImpl(readerService, printStream);
        printerService.printTest();

        assertEquals(QUESTION, outputStreamCaptor.toString().trim());
    }
}