package com.williams.userexercisesrest.repository;

import com.williams.userexercisesrest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findById(int id);
}
