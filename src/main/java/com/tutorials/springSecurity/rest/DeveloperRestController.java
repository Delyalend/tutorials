package com.tutorials.springSecurity.rest;

import com.tutorials.springSecurity.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/developers")
public class DeveloperRestController {
    List<Developer> developerList = Stream.of(
            new Developer(1L,"delyalend","delyalend"),
            new Developer(2L, "sadaw","sadaw"))
            .collect(Collectors.toList());


    @GetMapping
    @PreAuthorize("hasAuthority('developers:read')")
    public List<Developer> developers() {
        return developerList;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer add(@RequestBody Developer developer) {
        developerList.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void delete(@PathVariable Long id) {
        developerList.removeIf(developer -> developer.getId().equals(id));
    }

}
