package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Continent;
import com.aritoncosmin.ProiectSpringJava.repository.ContinentRepository;
import org.springframework.stereotype.Service;

@Service
public class ContinentService {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository){
        this.continentRepository = continentRepository;
    }

    public Continent findContinentByName(String name) {
        Continent continent = continentRepository.findContinentByName(name);

        if (continent == null)
            throw new NotFoundException("Continent with name " + name + " not found");

        return continent;
    }

}
