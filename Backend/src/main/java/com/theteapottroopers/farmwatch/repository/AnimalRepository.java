package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findAnimalByUuid(String uuid);
}
