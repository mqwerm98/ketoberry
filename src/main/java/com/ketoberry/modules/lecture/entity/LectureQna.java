package com.ketoberry.modules.lecture.entity;

import com.ketoberry.modules.account.entity.Account;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class LectureQna {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Account writer;

    @Enumerated(EnumType.STRING)
    private QnaType type;

    private String title;

    private String content;

}
