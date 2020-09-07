package com.ketoberry.modules.lecture.entity;

import com.ketoberry.modules.lecture.dto.LectureCardDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public LectureCard(Lecture lecture, LectureCardDto dto, int seq) {
        this.lecture = lecture;
        this.content = dto.getContent();
        this.image = dto.getImage();
        this.seq = seq;
        lecture.addCard(this);
    }

}
