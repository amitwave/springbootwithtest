package com.wave.springsample.cache;


import com.wave.springsample.model.Event;
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
