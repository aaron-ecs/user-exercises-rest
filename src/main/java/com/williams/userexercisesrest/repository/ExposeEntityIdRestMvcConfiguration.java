package com.williams.userexercisesrest.repository;

import com.williams.userexercisesrest.entity.ExerciseEntity;
import com.williams.userexercisesrest.entity.UserEntity;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class ExposeEntityIdRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(UserEntity.class,
                ExerciseEntity.class);
    }
}