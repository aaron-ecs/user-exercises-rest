package com.williams.userexercisesrest.controller;

import com.williams.userexercisesrest.entity.UserEntity;
import com.williams.userexercisesrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<UserEntity>(userService.saveUser(new UserEntity(userEntity.getName(),
                userEntity.getPostcode())), CREATED);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public ResponseEntity<UserEntity> returnUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.findUser(userId), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserEntity>> returnAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

}