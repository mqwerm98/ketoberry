package com.ketoberry.modules.community.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class CommunityCategory {

    @Id @GeneratedValue
    private Long id;

    private String name;

}
