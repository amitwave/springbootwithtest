package com.hackerrank.github.cache;


import com.hackerrank.github.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepoCache {

    private static List<Event> eventList = new ArrayList<>();

    public List<Event> getEventList() {
        return eventList;
    }
}
