package com.example.demo.Model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    @Relationship(type = "WATCHED")
    public Set<Movie> watchedMovies;

    @Relationship(type = "DIRECTED")
    public Set<Movie> directedMovies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void watch(Movie movie) {
        if (watchedMovies == null) {
            watchedMovies = new HashSet<>();
        }
        watchedMovies.add(movie);
    }

    public void direct(Movie movie) {
        if (directedMovies == null) {
            directedMovies = new HashSet<>();
        }
        directedMovies.add(movie);
    }
}
