package com.williams.userexercisesrest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "USER_EXERCISE_LOG")
public @Data class UserExerciseLogEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Getter
    @Setter
    private int id;

    @Column(name = "USER_ID")
    @Getter
    @Setter
    private int userId;

    @Column(name = "EXERCISE_ID")
    @Getter
    @Setter
    private int exerciseId;

    @Column(name = "DATE")
    @Getter
    @Setter
    private Date date;

    public UserExerciseLogEntity() {
    }

    public UserExerciseLogEntity(int userId, int exerciseId, Date date) {
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.date = date;
    }


}
