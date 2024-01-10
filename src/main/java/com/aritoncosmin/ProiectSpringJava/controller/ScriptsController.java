package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.service.ScriptsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/scripts")
public class ScriptsController {

    private final ScriptsService scriptsService;

    public ScriptsController(ScriptsService scriptsService){
        this.scriptsService = scriptsService;
    }

    @PostMapping("/reset_database")
    public ResponseEntity<Optional> resetDatabase(){
        scriptsService.resetDatabase();
        return ResponseEntity.ok().build();
    }

}
