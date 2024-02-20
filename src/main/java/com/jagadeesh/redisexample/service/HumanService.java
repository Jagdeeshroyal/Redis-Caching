package com.jagadeesh.redisexample.service;

import com.jagadeesh.redisexample.entity.Human;
import com.jagadeesh.redisexample.exception.HumanNotFoundByIdException;
import com.jagadeesh.redisexample.exception.HumanNotFoundException;
import com.jagadeesh.redisexample.repository.HumanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HumanService {

    @Autowired
    private HumanRepository repo;


    public Human introduceHuman(Human human) {
        return repo.save(human);
    }

    public List<Human> introduceHumans(List<Human> human) {
        return repo.saveAll(human);
    }

    public List<Human> getAll() {
        return repo.findAll();
    }

    public List<Human> getByName(String name) {
        return repo.findAllByName(name);
    }

    @Cacheable(value = "humans", key = "#id")
    public Human getById(int id) throws HumanNotFoundException {
        Human human = new Human();
        log.info("Getting the data from DB: " + id);
        human = repo.findById(id)
                .orElseThrow(() -> new HumanNotFoundByIdException("No Human available with Id:" + id));

        return human;
    }

    public Human updateHuman(Human human) {
        Human newHuman = repo.findById(human.getId())
                .orElseThrow(() -> new HumanNotFoundException("No Human available with Id:" + human.getId()));
        newHuman.setName(human.getName());
        return repo.save(newHuman);
    }

}
