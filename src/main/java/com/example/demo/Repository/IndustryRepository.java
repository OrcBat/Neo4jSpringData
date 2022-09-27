package com.example.demo.Repository;

import com.example.demo.Model.Industry;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface IndustryRepository extends Neo4jRepository<Industry, Long> {
    Optional<Industry> findByName(String name);
}
