package com.example.demo.Repository;


import com.example.demo.Model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository  extends Neo4jRepository<Person, Long> {
    Optional<Person> findByName(String name);

    List<Person> findByWatchedMoviesName(String name);
    List<Person> findByDirectedMoviesName(String name);
}
