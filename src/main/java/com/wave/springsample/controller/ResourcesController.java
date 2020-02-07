package com.wave.springsample.controller;

import com.wave.springsample.cache.RepoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    @Autowired
    private RepoCache repoCache;

    @DeleteMapping()
    public ResponseEntity erase(){
        repoCache.getEventList().clear();

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
