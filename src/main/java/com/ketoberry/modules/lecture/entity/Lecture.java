package com.ketoberry.modules.lecture.entity;

import com.ketoberry.modules.account.entity.Account;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Lecture {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Account writer;

    private String title;

    private long hits;

    private long likeNum;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "lecture")
    private Set<LectureTag> tags = new HashSet<>();

}
