package org.mybatis.spring.book;

import org.apache.ibatis.type.Alias;

@Alias("Book")
public class Book {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id:" + id + ",name:" + name + "}";
    }
}
