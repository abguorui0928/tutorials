package org.mybatis.spring.event;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository {

    List<Event> list();
}
