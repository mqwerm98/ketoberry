package com.ketoberry.modules.lecture.entity;

import com.ketoberry.modules.account.entity.Account;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class LectureLike {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}
