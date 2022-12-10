package ru.otus.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.test.service.TestService;
import ru.otus.test.service.TestServiceImpl;

@ComponentScan
public class TestDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestDemo.class);

        TestService testService = context.getBean(TestServiceImpl.class);
        testService.testUser();

        context.close();
    }
}
