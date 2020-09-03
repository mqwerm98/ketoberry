package com.ketoberry.modules.Tag.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Tag {

    @Id @GeneratedValue
    private Long id;

    private String name;

}
