package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.UserExercisesRestApplication;
import com.williams.userexercisesrest.entity.ExerciseEntity;
import exercise.Exercise;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rest.ExerciseTestDataCreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = UserExercisesRestApplication.class)
public class ExerciseServiceTest {

    @Autowired
    ExerciseService exerciseService;

    private ExerciseTestDataCreator exerciseTestDataCreator = new ExerciseTestDataCreator();

    @Test
    public void shouldCreateExercise() {
        //Given
        ExerciseEntity exercise = new ExerciseEntity("Test Description");

        //When
        ExerciseEntity createdExercise = exerciseService.saveExercise(exercise);

        //Then
        assertNotNull(exercise.getId());
        assertEquals(createdExercise.getDescription(), exercise.getDescription());
    }

    @Test
    public void shouldGetExercise() {
        //Given
        Exercise exercise = exerciseTestDataCreator.createExercise();

        //When
        ExerciseEntity exerciseFetched = exerciseService.findExercise(exercise.getId());

        //Then
        assertEquals(exercise.getId(), exerciseFetched.getId());
        assertEquals(exercise.getDescription(), exerciseFetched.getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void shouldDeleteExercise() {
        //Given
        Exercise exercise = exerciseTestDataCreator.createExercise();

        //When
        exerciseService.deleteExercise(exercise.getId());

        //Then
        Assert.assertNull(exerciseService.findExercise(exercise.getId()));
    }

}