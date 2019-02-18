package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.entity.UserEntity;
import com.williams.userexercisesrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(new UserEntity(userEntity.getName(),
                userEntity.getPostcode()));
    }

    public UserEntity findUser(int id) {
        UserEntity userEntity = userRepository.findById(id);
        userEntity.getId();
        return userEntity;
    }

    public List<UserEntity> findAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
