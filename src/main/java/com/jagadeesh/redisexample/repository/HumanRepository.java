package com.jagadeesh.redisexample.repository;

import com.jagadeesh.redisexample.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanRepository extends JpaRepository<Human,Integer> {
    List<Human> findAllByName(String name);

    @Procedure("GET_HUMAN_BY_NAME")
    List<Human> getHumanWithName(String name_nm);
}
