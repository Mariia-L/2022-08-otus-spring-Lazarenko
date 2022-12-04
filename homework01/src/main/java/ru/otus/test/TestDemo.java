package ru.otus.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.test.service.TestPrinterService;

public class TestDemo
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TestPrinterService testPrinter = context.getBean(TestPrinterService.class);
        testPrinter.printTest();

        context.close();
    }
}
