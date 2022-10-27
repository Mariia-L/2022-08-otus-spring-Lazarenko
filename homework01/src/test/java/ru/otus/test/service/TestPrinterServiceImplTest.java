package ru.otus.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.test.dao.TestDao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestPrinterServiceImplTest {

    private static final String QUESTION = "How are you?";

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printEmpty() {

        TestReaderService readerService = Mockito.mock(TestReaderServiceImpl.class);
        Mockito.when(readerService.getQuestions()).thenReturn(Collections.emptyList());

        TestPrinterService printerService = new TestPrinterServiceImpl(readerService);
        printerService.printTest();

        assertEquals("", outputStreamCaptor.toString());
    }

    @Test
    public void printList() {

        List<TestDao> tests = new ArrayList<>(2);
        TestDao test = new TestDao();
        test.setId(1);
        test.setQuestion(QUESTION);

        tests.add(test);

        TestReaderService readerService = Mockito.mock(TestReaderServiceImpl.class);
        Mockito.when(readerService.getQuestions()).thenReturn(tests);

        TestPrinterService printerService = new TestPrinterServiceImpl(readerService);
        printerService.printTest();

        assertEquals(QUESTION, outputStreamCaptor.toString().trim());
    }
}