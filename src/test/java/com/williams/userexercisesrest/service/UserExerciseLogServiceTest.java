package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.UserExerciseLog;
import com.williams.userexercisesrest.UserExercisesRestApplication;
import com.williams.userexercisesrest.entity.UserExerciseLogEntity;
import exercise.Exercise;
import log.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rest.ExerciseTestDataCreator;
import rest.LogTestDataCreator;
import rest.UserTestDataCreator;
import user.User;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = UserExercisesRestApplication.class)
public class UserExerciseLogServiceTest {

    @Autowired
    UserExerciseLogService userExerciseLogService;

    private UserTestDataCreator userTestDataCreator = new UserTestDataCreator();
    private ExerciseTestDataCreator exerciseTestDataCreator = new ExerciseTestDataCreator();
    private LogTestDataCreator logTestDataCreator = new LogTestDataCreator();

    @Test
    public void shouldCreateLog() {
        //Given
        User user = userTestDataCreator.createUser();
        Exercise exercise = exerciseTestDataCreator.createExercise();
        UserExerciseLogEntity userExerciseLogEntity = new UserExerciseLogEntity();
        userExerciseLogEntity.setUserId(user.getId());
        userExerciseLogEntity.setExerciseId(exercise.getId());
        userExerciseLogEntity.setDate(new Date(2010));

        //When
        UserExerciseLog logReturned = userExerciseLogService.saveLogEntry(userExerciseLogEntity);

        //Then
        assertNotNull(logReturned.getId());
        assertEquals(user.getName(), logReturned.getUserName());
        assertEquals(user.getId(), logReturned.getUserId());
        assertEquals(exercise.getDescription(), logReturned.getDescription());
        assertEquals(exercise.getId(), logReturned.getExerciseId());
    }

    @Test
    public void shouldGetLog() {
        //Given
        Log log = logTestDataCreator.createLog();

        //When
        UserExerciseLog logReturned = userExerciseLogService.getLogEntryDetails(log.getId());

        //Then
        assertNotNull(logReturned.getId());
        assertEquals(log.getUserName(), logReturned.getUserName());
        assertEquals(log.getUserId(), logReturned.getUserId());
        assertEquals(log.getDescription(), logReturned.getDescription());
        assertEquals(log.getExerciseId(), logReturned.getExerciseId());
        assertEquals(log.getDate().toString(), logReturned.getDate().toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldDeleteLog() {
        //Given
        Log log = logTestDataCreator.createLog();

        //When
        userExerciseLogService.deleteLogEntry(log.getId());

        //Then
        Assert.assertNull(userExerciseLogService.getLogEntryDetails(log.getId()));
    }

}