package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.entity.ExerciseEntity;
import com.williams.userexercisesrest.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    public ExerciseEntity saveExercise(ExerciseEntity exerciseEntity) {
        return exerciseRepository.save(new ExerciseEntity(exerciseEntity.getDescription()));
    }

    public ExerciseEntity findExercise(int id) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(id);
        exerciseEntity.getId();
        return exerciseEntity;
    }

    public List<ExerciseEntity> findAllExercises() {
        return (List<ExerciseEntity>) exerciseRepository.findAll();
    }

    public void deleteExercise(int id) {
        exerciseRepository.delete(id);
    }
}
