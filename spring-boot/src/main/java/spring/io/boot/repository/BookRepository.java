package spring.io.boot.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import spring.io.boot.model.Book;

import com.google.common.collect.Lists;

@Component
public class BookRepository {

    @Cacheable(value = "books")
    public Book find(String isbn) {
        System.out.println("load book from local");
        return null;
    }

    @CachePut(value = "books", key = "#book.getIsbn()")
    public Book update(Book book) {
        System.out.println("update book");
        return book;
    }

    @CacheEvict(value = "books", allEntries = true)
    public void clearAll() {
        System.out.println("clearAll");
    }

    @CacheEvict(value = "books", key = "#id")
    public void delete(String id) {
        System.out.println("delete");
    }

    @Cacheable(value = "books")
    public List<Book> findList() {
        return Lists.newArrayList(new Book("book6", "book6"), new Book("book7", "book7"));
    }
}
