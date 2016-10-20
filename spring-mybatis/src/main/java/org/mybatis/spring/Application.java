package org.mybatis.spring;

import org.mybatis.spring.book.BookRepository;
import org.mybatis.spring.event.EventRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        EventRepository eventRepository = ctx.getBean(EventRepository.class);
        System.out.println(eventRepository.list());

        BookRepository bookRepository = ctx.getBean(BookRepository.class);
        System.out.println(bookRepository.list());
        ctx.close();
    }
}
