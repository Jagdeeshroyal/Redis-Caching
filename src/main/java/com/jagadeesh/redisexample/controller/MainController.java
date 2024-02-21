package com.jagadeesh.redisexample.controller;

import com.jagadeesh.redisexample.entity.Human;
import com.jagadeesh.redisexample.service.HumanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

    @Autowired
    private HumanService service;

    @GetMapping
    public String getMessage() {
        return " Hello Big People";
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Human>> getAllHumans() { // return all Humans
        List<Human> humans = service.getAll();
        return ResponseEntity.ok(humans);
    }

    @GetMapping("/name/{name}")
    public List<Human> getHumansByName(@PathVariable String name) { // return all Humans with name
        return service.getByName(name);
    }

    @GetMapping("/id/{id}")
    public Human getById(@PathVariable int id) throws Exception {
        Human human = service.getById(id);
        return human;
    }

    @PostMapping("/add")
    public Human addHuman(@RequestBody Human human) {
        human = service.introduceHuman(human);
        return human;
    }

    @PostMapping("/add-all")
    public List<Human> addHumans(@RequestBody List<Human> humans) {
        humans = service.introduceHumans(humans);
        return humans;
    }


    @PutMapping()
    public Human updateHuman(@RequestBody Human human) {
        return service.updateHuman(human);
    }


    @DeleteMapping({"delete/{id}"})
    public Human deleteHuman(@PathVariable int id) {
        Human human = service.getById(id);
        service.deleteHuman(human);
        return human;
    }

}
