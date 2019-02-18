package com.williams.userexercisesrest.controller;

import com.google.gson.Gson;
import com.williams.userexercisesrest.entity.ExerciseEntity;
import com.williams.userexercisesrest.repository.ExerciseRepository;
import com.williams.userexercisesrest.service.ExerciseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExerciseController.class)
public class ExerciseControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ExerciseRepository mockedExerciseRepository;

    @MockBean
    ExerciseService mockedExerciseService;

    @InjectMocks
    private ExerciseController exerciseController;

    private Gson gson = new Gson();

    private ExerciseEntity exerciseEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        exerciseEntity = new ExerciseEntity("Exercise Description");
    }

    @Test
    public void shouldCreateExercise() throws Exception {
        when(mockedExerciseService.saveExercise(any(ExerciseEntity.class)))
                .thenReturn(exerciseEntity);

        mockMvc.perform(post("/api/exercises/")
                .content(gson.toJson(exerciseEntity))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetExercise() throws Exception {
        when(mockedExerciseService.findExercise(any(Integer.class)))
                .thenReturn(exerciseEntity);

        mockMvc.perform(get("/api/exercises/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404() throws Exception {
        when(mockedExerciseService.findExercise(any(Integer.class)))
                .thenThrow(NullPointerException.class);

        mockMvc.perform(get("/api/exercises/{id}", 1))
                .andExpect(status().isNotFound());
    }

}