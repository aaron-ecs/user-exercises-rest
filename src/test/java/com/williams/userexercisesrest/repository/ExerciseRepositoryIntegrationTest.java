package com.williams.userexercisesrest.repository;

import com.williams.userexercisesrest.entity.ExerciseEntity;
import com.williams.userexercisesrest.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ExerciseRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    public void findById() throws Exception {
        //Given
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setDescription("Exercise Description");
        entityManager.persist(exerciseEntity);

        //When
        ExerciseEntity returnExercise = exerciseRepository.findById(exerciseEntity.getId());

        //Then
        assertEquals("Exercise Description", returnExercise.getDescription());
    }

}