package com.aritoncosmin.ProiectSpringJava;

import com.aritoncosmin.ProiectSpringJava.service.ScriptsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProiectSpringJavaApplication implements CommandLineRunner {

    private final ScriptsService scriptsService;

    public ProiectSpringJavaApplication(ScriptsService scriptsService){
        this.scriptsService = scriptsService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProiectSpringJavaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        scriptsService.resetDatabase();
    }
}
