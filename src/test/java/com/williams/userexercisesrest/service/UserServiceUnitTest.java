package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.entity.UserEntity;
import com.williams.userexercisesrest.repository.UserRepository;
import com.williams.userexercisesrest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity mockedUser = new UserEntity();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockedUser.setName("Name");
        mockedUser.setPostcode("AAAAAAA");
    }

    @Test
    public void shouldCreateUser() {
        //Given
        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(mockedUser);

        //When
        UserEntity anotherUser = userService.saveUser(mockedUser);

        //Then
        assertEquals(mockedUser.getPostcode(), anotherUser.getPostcode());
    }

    @Test
    public void shouldGetUser() {
        //Given
        when(userRepository.findById(any(Integer.class)))
                .thenReturn(mockedUser);

        //When
        UserEntity anotherUser = userService.findUser(1);

        //Then
        assertEquals(mockedUser.getPostcode(), anotherUser.getPostcode());
    }

}