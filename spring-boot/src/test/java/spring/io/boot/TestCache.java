package spring.io.boot;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import spring.io.boot.model.Book;
import spring.io.boot.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCache {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager   cacheManager;

    @Test
    public void test() throws InterruptedException {
        Book book1 = bookRepository.find("book1");
        Assert.assertNull(book1);
        bookRepository.update(new Book("book2", "book2"));

        Book book2 = bookRepository.find("book2");
        Assert.assertEquals("book2", book2.getTitle());

        bookRepository.delete("book2");
        Book book3 = bookRepository.find("book2");
        Assert.assertNull(book3);
        List<Book> list = bookRepository.findList();
        Assert.assertEquals(2, list.size());

        Book book6 = bookRepository.find("book6");
        Assert.assertNull(book6);
    }
}
