package com.ketoberry.modules.lecture.entity;

import com.ketoberry.modules.Tag.entity.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureTag {

    @Id @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public LectureTag(Lecture lecture, Tag tag) {
        this.lecture = lecture;
        this.tag = tag;
        lecture.addTag(this);
    }
}
