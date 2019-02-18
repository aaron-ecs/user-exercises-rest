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
@Table(name = "USER")
public @Data class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Getter
    @Setter
    private int id;

    @Column(name = "NAME")
    @Length(min = 1, max = 35)
    @Getter
    @Setter
    private String name;

    @Column(name = "POSTCODE")
    @Length(min = 6, max = 8)
    @Getter
    @Setter
    private String postcode;

    public UserEntity() {
    }

    public UserEntity(String name, String postcode) {
        this.name = name;
        this.postcode = postcode;
    }

}
