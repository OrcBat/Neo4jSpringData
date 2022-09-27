package com.example.demo.Model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "BELONGS_TO")
    public Set<Industry> industries;

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



    public void addIndustry(Industry industry) {
        if (industries == null) {
            industries = new HashSet<>();
        }
        industries.add(industry);
    }

}
