package org.mybatis.spring.book;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {

    List<Book> list();
}
