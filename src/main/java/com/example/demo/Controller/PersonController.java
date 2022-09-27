package com.example.demo.Controller;

import com.example.demo.Model.Movie;
import com.example.demo.Model.Person;
import com.example.demo.Repository.MovieRepository;
import com.example.demo.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Person> getPersons(@PathVariable String id) {
        return personRepository.findById(Long.valueOf(id));
    }

    @PostMapping("")
    public Person saveUser(@RequestBody Person person) {
        Optional<Person> oldPerson = personRepository.findByName(person.getName());
        AtomicReference<Person> newPerson = new AtomicReference<>(new Person());
        oldPerson.ifPresent(m -> {
            newPerson.set(m);
        });
        newPerson.get().setName(person.getName());
        return personRepository.save(newPerson.get());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        Optional<Person> person = personRepository.findById(Long.valueOf(id));
        person.ifPresent(p -> {
            personRepository.delete(p);
        });
    }

    @PostMapping("watch-movie")
    public Person watchMovie(@RequestBody HashMap<String, String> data) {
        Optional<Person> oldPerson = personRepository.findByName(data.get("personName"));
        AtomicReference<Person> newPerson = new AtomicReference<>(new Person());
        oldPerson.ifPresent(m -> {
            newPerson.set(m);
        });
        newPerson.get().setName(data.get("personName"));
        personRepository.save(newPerson.get());


        Optional<Movie> oldMovie = movieRepository.findByName(data.get("movieName"));
        AtomicReference<Movie> newMovie = new AtomicReference<>(new Movie());
        oldMovie.ifPresent(m -> {
            newMovie.set(m);
        });
        newMovie.get().setName(data.get("movieName"));
        movieRepository.save(newMovie.get());

        newPerson.get().watch(newMovie.get());
        personRepository.save(newPerson.get());
        return newPerson.get();
    }


    @PostMapping("direct-movie")
    public Person directMovie(@RequestBody HashMap<String, String> data) {
        Optional<Person> oldPerson = personRepository.findByName(data.get("personName"));
        AtomicReference<Person> newPerson = new AtomicReference<>(new Person());
        oldPerson.ifPresent(m -> {
            newPerson.set(m);
        });
        newPerson.get().setName(data.get("personName"));
        personRepository.save(newPerson.get());


        Optional<Movie> oldMovie = movieRepository.findByName(data.get("movieName"));
        AtomicReference<Movie> newMovie = new AtomicReference<>(new Movie());
        oldMovie.ifPresent(m -> {
            newMovie.set(m);
        });
        newMovie.get().setName(data.get("movieName"));
        movieRepository.save(newMovie.get());

        newPerson.get().direct(newMovie.get());
        personRepository.save(newPerson.get());
        return newPerson.get();
    }
}
