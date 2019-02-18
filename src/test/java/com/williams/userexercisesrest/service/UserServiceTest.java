package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.UserExercisesRestApplication;
import com.williams.userexercisesrest.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rest.UserTestDataCreator;
import user.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = UserExercisesRestApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    private UserTestDataCreator userTestDataCreator = new UserTestDataCreator();


    @Test
    public void shouldCreateUser() {
        //Given
        UserEntity user = new UserEntity("Test Name", "AA12AAA");

        //When
        UserEntity createdUser = userService.saveUser(user);

        //Then
        assertNotNull(createdUser.getId());
        assertEquals(createdUser.getName(), user.getName());
        assertEquals(createdUser.getPostcode(), user.getPostcode());
    }

    @Test
    public void shouldGetUser() {
        //Given
        UserTestDataCreator userTestDataCreator = new UserTestDataCreator();
        User user = userTestDataCreator.createUser();

        //When
        UserEntity userFetched = userService.findUser(user.getId());

        //Then
        assertEquals(user.getName(), userFetched.getName());
        assertEquals(user.getPostcode(), userFetched.getPostcode());
    }

    @Test(expected = NullPointerException.class)
    public void shouldDeleteUser() {
        //Given
        User user = userTestDataCreator.createUser();

        //When
        userService.deleteUser(user.getId());

        //Then
        Assert.assertNull(userService.findUser(user.getId()));
    }

}