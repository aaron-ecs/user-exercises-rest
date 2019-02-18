package com.williams.userexercisesrest.repository;

import com.williams.userexercisesrest.entity.ExerciseEntity;
import com.williams.userexercisesrest.entity.UserEntity;
import com.williams.userexercisesrest.entity.UserExerciseLogEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserExerciseLogRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserExerciseLogRepository userExerciseLogRepository;

    @Test
    public void shouldFindById() throws Exception {
        //Given
        UserEntity user = new UserEntity();
        user.setName("User Name");
        user.setPostcode("AA121LG");
        entityManager.persist(user);

        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setDescription("Exercise Description");
        entityManager.persist(exerciseEntity);

        UserExerciseLogEntity userExerciseLogEntity = new UserExerciseLogEntity();
        userExerciseLogEntity.setUserId(user.getId());
        userExerciseLogEntity.setExerciseId(exerciseEntity.getId());
        userExerciseLogEntity.setDate(new Date(100));
        entityManager.persist(userExerciseLogEntity);

        //When
        UserExerciseLogEntity returnLog = userExerciseLogRepository.findById(userExerciseLogEntity.getId());

        //Then
        assertEquals(userExerciseLogEntity.getUserId(), returnLog.getUserId());

    }

}