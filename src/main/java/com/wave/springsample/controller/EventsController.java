package com.wave.springsample.controller;

import com.wave.springsample.cache.RepoCache;
import com.wave.springsample.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/events")
public class EventsController {


    @Autowired
    private RepoCache repoCache;

    @PostMapping()
    public ResponseEntity events(@RequestBody Event event){

        List<Event> events = getEvents(event.getId());
        if(events == null || events.isEmpty()) {


        repoCache.getEventList().add(event);

        return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new  ResponseEntity(HttpStatus.BAD_REQUEST);
        }



    }

    @GetMapping()
    public ResponseEntity events(){


        return ResponseEntity.ok(repoCache.getEventList());

    }


    @GetMapping("/{id}")
    public ResponseEntity filterEvents(@PathVariable Long id){

        if(id == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<Event> events = getEvents(id);

        if(events== null || events.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{

            return ResponseEntity.ok(events.get(0));
        }
    }



    @GetMapping("/repos/{id}")
    public ResponseEntity filterEventsByRepoId(@PathVariable Long id){

        if(id == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<Event> events = getEventsByRepoId(id);

        if(events== null || events.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{

            return ResponseEntity.ok(events);
        }
    }

    @GetMapping("/actors/{id}")
    public ResponseEntity filterEventsByActorId(@PathVariable Long id){

        if(id == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<Event> events = getEventsByActorsId(id);

        if(events== null || events.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{

            return ResponseEntity.ok(events);
        }
    }


    @GetMapping("/repos/{repoId}/actors/{actorId}")
    public ResponseEntity filterEventsByRepoIdAndActorId(@PathVariable Long repoId, @PathVariable Long actorId){

        if(repoId == null || actorId == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<Event> events = getEventsByRepoIdAndActorsId(repoId, actorId);

        if(events== null || events.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{

            return ResponseEntity.ok(events);
        }
    }

    private List<Event> getEventsByRepoIdAndActorsId(Long repoId, Long actorId) {
        return repoCache.getEventList().stream().filter(event -> event.getRepo().getId().equals(repoId) && event.getActor().getId().equals(actorId)).collect(Collectors.toList());
    }


    private List<Event> getEventsByActorsId(Long id) {
        return repoCache.getEventList().stream().filter(event -> event.getActor().getId().equals(id)).collect(Collectors.toList());
    }

    private List<Event> getEventsByRepoId(Long id) {
        return repoCache.getEventList().stream().filter(event -> event.getRepo().getId().equals(id)).collect(Collectors.toList());
    }


    private List<Event> getEvents(@PathVariable Long id) {
        return repoCache.getEventList().stream().filter(event -> event.getId().equals(id)).collect(Collectors.toList());
    }


}
