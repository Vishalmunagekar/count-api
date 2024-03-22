package com.myapp.countapi.repository;

import com.myapp.countapi.entities.CountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CountRepository extends JpaRepository<CountEntity, Long> {
    Optional<CountEntity> findByApiKey(String key);
    Optional<CountEntity> findByUsername(String username);
}
