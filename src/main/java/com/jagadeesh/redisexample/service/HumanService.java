package com.jagadeesh.redisexample.service;

import com.jagadeesh.redisexample.entity.Human;
import com.jagadeesh.redisexample.exception.HumanNotFoundByIdException;
import com.jagadeesh.redisexample.exception.HumanNotFoundException;
import com.jagadeesh.redisexample.repository.HumanRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Transactional
    public List<Human> getByName(String name) {
        repo.getHumanWithName(name).stream().forEach(System.out::println);//calling the procedure
        return repo.findAllByName(name);
    }

    @Cacheable(value = "humans",key = "#id")
    public Human getById(int id) throws HumanNotFoundException {
        Human human = new Human();
        log.info("Getting the data from DB: " + id);
        human = repo.findById(id)
                .orElseThrow(() -> new HumanNotFoundByIdException("No Human available with Id:" + id));

        return human;
    }

    @CachePut(value = "humans",key = "#human.id")
    public Human updateHuman(Human human) {
        Human newHuman = repo.findById(human.getId())
                .orElseThrow(() -> new HumanNotFoundException("No Human available with Id:" + human.getId()));
        newHuman.setName(human.getName());
        return repo.save(newHuman);
    }

    @CacheEvict(value = "humans",key = "#id")
    public Human deleteHuman(Human human) {
        log.info("Deleting the Human with Id:" + human.getId());
        repo.delete(human);
        return human ;
    }
}
