package com.example.chaos.monkey.chaosdemo.service;

import com.example.chaos.monkey.chaosdemo.repo.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Benjamin Wilms
 */
@Service
public class GreetingService {

    public static final String GREETINGS_FROM_THE_SERVER_SIDE = "Greetings from the server side!";
    public static final String GREETINGS_FROM_THE_PAGING_AND_SORTING_DATABASE_SIDE = "Greetings from the paging and sorting database side";
    private final HelloRepo helloRepo;
    private final HelloRepoSearchAndSorting repoSearchAndSorting;
    private final HelloRepoJpa helloRepoJpa;
    private final HelloRepoAnnotation helloRepoAnnotation;


    public GreetingService(HelloRepo helloRepo, HelloRepoSearchAndSorting repoSearchAndSorting, HelloRepoJpa helloRepoJpa,
        HelloRepoAnnotation helloRepoAnnotation) {
        this.helloRepo = helloRepo;
        this.repoSearchAndSorting = repoSearchAndSorting;
        this.helloRepoJpa = helloRepoJpa;
        this.helloRepoAnnotation = helloRepoAnnotation;
    }

    public String greet() {
        return GREETINGS_FROM_THE_SERVER_SIDE;
    }

    public String greetFromRepo() {
        Hello databaseSide = helloRepo.save(new Hello(0, "Greetings from the database side"));
        return databaseSide.getMessage();
    }

    public String greetFromRepoPagingSorting() {
        Hello databaseSide = repoSearchAndSorting.save(new Hello(0, GREETINGS_FROM_THE_PAGING_AND_SORTING_DATABASE_SIDE));
        Optional<Hello> byId = repoSearchAndSorting.findById(databaseSide.getId());
        return byId.orElse(new Hello(-99, "not found")).getMessage();
    }

    public String greetFromRepoJpa() {
        Hello databaseSide = helloRepoJpa.save(new Hello(0, GREETINGS_FROM_THE_PAGING_AND_SORTING_DATABASE_SIDE));
        Optional<Hello> byId = helloRepoJpa.findById(databaseSide.getId());
        return byId.orElse(new Hello(-99, "not found")).getMessage();
    }

    public String greetFromRepoAnnotation() {
        Hello databaseSide = helloRepoAnnotation.save(new Hello(0, GREETINGS_FROM_THE_PAGING_AND_SORTING_DATABASE_SIDE));
        Optional<Hello> byId = helloRepoAnnotation.findById(databaseSide.getId());
        return byId.orElse(new Hello(-99, "not found")).getMessage();
    }
}
