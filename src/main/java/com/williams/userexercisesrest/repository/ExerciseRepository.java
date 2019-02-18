package com.williams.userexercisesrest.repository;

import com.williams.userexercisesrest.entity.ExerciseEntity;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<ExerciseEntity, Integer> {
    ExerciseEntity findById(int id);
}

