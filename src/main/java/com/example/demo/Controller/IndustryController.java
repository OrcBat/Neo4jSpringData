package com.example.demo.Controller;

import com.example.demo.Model.Industry;
import com.example.demo.Model.Movie;
import com.example.demo.Repository.IndustryRepository;
import com.example.demo.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/industry")
public class IndustryController {

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    private List<Industry> getAllIndustries() {
        return industryRepository.findAll();
    }

    @PostMapping("")
    private Industry saveIndustry(@RequestBody Industry industry) {
        Optional<Industry> oldIndustry = industryRepository.findByName(industry.getName());
        AtomicReference<Industry> newIndustry = new AtomicReference<>(new Industry());
        oldIndustry.ifPresent(m -> {
            newIndustry.set(m);
        });
        newIndustry.get().setName(industry.getName());
        return industryRepository.save(newIndustry.get());
    }


    @DeleteMapping("{id}")
    public void deleteIndustry(@PathVariable String id) {
        Optional<Industry> industry = industryRepository.findById(Long.valueOf(id));
        industry.ifPresent(p -> {
            industryRepository.delete(p);
        });
    }


    @PostMapping("connect-industry")
    public Movie connectIndustry(@RequestBody HashMap<String, String> data) {
        Optional<Movie> oldMovie = movieRepository.findByName(data.get("movieName"));
        AtomicReference<Movie> newMovie = new AtomicReference<>(new Movie());
        oldMovie.ifPresent(m -> {
            newMovie.set(m);
        });
        newMovie.get().setName(data.get("movieName"));
        movieRepository.save(newMovie.get());


        Optional<Industry> oldIndustry = industryRepository.findByName(data.get("industryName"));
        AtomicReference<Industry> newIndustry = new AtomicReference<>(new Industry());
        oldIndustry.ifPresent(m -> {
            newIndustry.set(m);
        });
        newIndustry.get().setName(data.get("industryName"));
        industryRepository.save(newIndustry.get());

        newMovie.get().addIndustry(newIndustry.get());
        movieRepository.save(newMovie.get());
        return newMovie.get();
    }


}
