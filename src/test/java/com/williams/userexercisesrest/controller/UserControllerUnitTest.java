package com.williams.userexercisesrest.controller;

import com.google.gson.Gson;
import com.williams.userexercisesrest.entity.UserEntity;
import com.williams.userexercisesrest.repository.UserRepository;
import com.williams.userexercisesrest.service.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService mockedUserService;

    @InjectMocks
    private UserController userController;

    private Gson gson = new Gson();

    private UserEntity mockedUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockedUser = new UserEntity("Mocked", "AA221FG");
    }

    @Test
    public void shouldCreateUser() throws Exception {
        when(mockedUserService.saveUser(any(UserEntity.class)))
                .thenReturn(mockedUser);

        mockMvc.perform(post("/api/users")
                .content(gson.toJson(mockedUser))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetUser() throws Exception {
        when(mockedUserService.findUser(any(Integer.class)))
                .thenReturn(mockedUser);

        mockMvc.perform(get("/api/users/{id}", 1))
                .andExpect(status().isOk());
    }

}
