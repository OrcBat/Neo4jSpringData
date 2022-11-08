package com.example.demo.Repository;


import com.example.demo.Model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface MovieRepository  extends Neo4jRepository<Movie, Long> {
    Optional<Movie> findByName(String name);
}
