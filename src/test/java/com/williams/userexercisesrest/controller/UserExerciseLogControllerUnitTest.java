package com.williams.userexercisesrest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.williams.userexercisesrest.UserExerciseLog;
import com.williams.userexercisesrest.entity.UserExerciseLogEntity;
import com.williams.userexercisesrest.repository.UserExerciseLogRepository;
import com.williams.userexercisesrest.service.UserExerciseLogService;
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

import java.sql.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserExerciseLogController.class)
public class UserExerciseLogControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserExerciseLogRepository userExerciseLogRepository;

    @MockBean
    private UserExerciseLogService userExerciseLogService;

    @InjectMocks
    private UserExerciseLogController userExerciseLogController;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    private UserExerciseLogEntity userExerciseLogEntity;

    private UserExerciseLog userExerciseLog;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userExerciseLogEntity = new UserExerciseLogEntity(1, 1, new Date(1l));
        userExerciseLog = new UserExerciseLog();
    }

    @Test
    public void shouldPostLog() throws Exception {
        when(userExerciseLogService.saveLogEntry(any(UserExerciseLogEntity.class)))
                .thenReturn(userExerciseLog);

        mockMvc.perform(post("/api/log")
                .content(gson.toJson(userExerciseLogEntity))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetLog() throws Exception {
        when(userExerciseLogService.getLogEntryDetails(any(Integer.class)))
                .thenReturn(userExerciseLog);

        mockMvc.perform(get("/api/log/{id}", 1)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}