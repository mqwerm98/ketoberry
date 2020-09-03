package com.ketoberry.modules.lecture.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class LectureCard {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    private int seq;

    private String content;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String image;

}
