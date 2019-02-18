package com.williams.userexercisesrest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXERCISE")
public @Data class ExerciseEntity {


    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Getter
    @Setter
    private int id;

    @Column(name = "DESCRIPTION")
    @Length(min = 1, max= 255)
    @Getter
    @Setter
    private String description;

    public ExerciseEntity() {
    }

    public ExerciseEntity(String description) {
        this.description = description;
    }

}
