package com.example.tz.Repositories;

import com.example.tz.Models.Entities.AmenityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<AmenityEntity, Integer> {
    Optional<AmenityEntity> findByName(String name);
}
