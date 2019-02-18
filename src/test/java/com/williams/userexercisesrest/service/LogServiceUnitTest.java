package com.williams.userexercisesrest.service;

import com.williams.userexercisesrest.UserExerciseLog;
import com.williams.userexercisesrest.entity.ExerciseEntity;
import com.williams.userexercisesrest.entity.UserEntity;
import com.williams.userexercisesrest.entity.UserExerciseLogEntity;
import com.williams.userexercisesrest.repository.ExerciseRepository;
import com.williams.userexercisesrest.repository.UserExerciseLogRepository;
import com.williams.userexercisesrest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LogServiceUnitTest {

    @Mock
    private UserExerciseLogRepository userExerciseLogRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserExerciseLogService userExerciseLogService;

    private UserExerciseLogEntity mockedLog = new UserExerciseLogEntity();
    private ExerciseEntity mockedExercise = new ExerciseEntity();
    private UserEntity mockedUser = new UserEntity();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockedLog.setId(1);
        mockedLog.setUserId(1);
        mockedLog.setExerciseId(1);
        mockedLog.setDate(new Date(2010));
        mockedExercise.setId(1);
        mockedExercise.setDescription("SADFSDFSD");
        mockedUser.setId(1);
        mockedUser.setName("SDFSDF");
        mockedUser.setPostcode("SDFSDF");
    }

    @Test
    public void shouldCreateLog() {
        //Given
        when(userExerciseLogRepository.save(any(UserExerciseLogEntity.class)))
                .thenReturn(mockedLog);

        when(userExerciseLogRepository.findById(any(Integer.class)))
                .thenReturn(mockedLog);

        when(exerciseRepository.findById((any(Integer.class))))
                .thenReturn(mockedExercise);

        when(userRepository.findById((any(Integer.class))))
                .thenReturn(mockedUser);

        //When
        UserExerciseLog returnedLog = userExerciseLogService.saveLogEntry(new UserExerciseLogEntity(1, 1, new Date(2010)));

        //Then
        assertEquals(mockedExercise.getDescription(), returnedLog.getDescription());
    }

    @Test
    public void shouldGetLog() {
        //Given
        when(exerciseRepository.findById((any(Integer.class))))
                .thenReturn(mockedExercise);

        when(userRepository.findById((any(Integer.class))))
                .thenReturn(mockedUser);

        when(userExerciseLogRepository.findById((any(Integer.class))))
                .thenReturn(mockedLog);

        //When
        UserExerciseLog returnedLog = userExerciseLogService.getLogEntryDetails(1);

        //Then
        assertEquals(mockedUser.getName(), returnedLog.getUserName());
    }

}