package com.williams.userexercisesrest.repository;

import com.williams.userexercisesrest.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindById() {
        //Given
        UserEntity user = new UserEntity();
        user.setName("User Name");
        user.setPostcode("AA121LG");
        entityManager.persist(user);

        //When
        UserEntity userReturned = userRepository.findById(user.getId());

        //Then
        assertEquals(user.getName(), userReturned.getName());
    }
}