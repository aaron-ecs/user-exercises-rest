package com.williams.userexercisesrest.repository;

import com.williams.userexercisesrest.entity.UserExerciseLogEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserExerciseLogRepository extends CrudRepository<UserExerciseLogEntity, Integer> {
    UserExerciseLogEntity findById(int id);
}
