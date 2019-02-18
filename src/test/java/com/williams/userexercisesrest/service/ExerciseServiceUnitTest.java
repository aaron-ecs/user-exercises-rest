package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.entity.ExerciseEntity;
import com.williams.userexercisesrest.repository.ExerciseRepository;
import com.williams.userexercisesrest.service.ExerciseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ExerciseServiceUnitTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    private ExerciseEntity mockedExercise = new ExerciseEntity();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockedExercise.setDescription("Description");
    }

    @Test
    public void shouldCreateExercise() {
        //Given
        when(exerciseRepository.save(any(ExerciseEntity.class)))
                .thenReturn(mockedExercise);

        //When
        ExerciseEntity returnedExercise = exerciseService.saveExercise(mockedExercise);

        //Then
        assertEquals(mockedExercise.getDescription(), returnedExercise.getDescription());
    }

    @Test
    public void shouldGetExercise() {
        //Given
        when(exerciseRepository.findById(any(Integer.class)))
                .thenReturn(mockedExercise);

        //When
        ExerciseEntity returnedExercise = exerciseService.findExercise(1);

        //Then
        assertEquals(mockedExercise.getDescription(), returnedExercise.getDescription());
    }

}