package com.ketoberry.modules.lecture.entity;

import com.ketoberry.modules.Tag.entity.Tag;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class LectureTag {

    @Id @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
