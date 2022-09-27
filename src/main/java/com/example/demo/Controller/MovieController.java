package com.example.demo.Controller;


import com.example.demo.Model.Movie;
import com.example.demo.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Movie> getMovie(@PathVariable String id) {
        return movieRepository.findById(Long.valueOf(id));
    }

    @PostMapping("")
    public Movie saveMovie(@RequestBody Movie movie) {
        Optional<Movie> oldMovie = movieRepository.findByName(movie.getName());
        AtomicReference<Movie> newMovie = new AtomicReference<>(new Movie());
        oldMovie.ifPresent(m -> {
            newMovie.set(m);
        });
        newMovie.get().setName(movie.getName());
        return movieRepository.save(newMovie.get());

    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable String id) {
        Optional<Movie> movie = movieRepository.findById(Long.valueOf(id));
        movie.ifPresent(p -> {
            movieRepository.delete(p);
        });
    }
}
