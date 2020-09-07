package com.ketoberry.modules.lecture.entity;

import com.ketoberry.modules.Tag.entity.Tag;
import com.ketoberry.modules.account.entity.Account;
import com.ketoberry.modules.lecture.dto.LectureCardDto;
import com.ketoberry.modules.lecture.dto.LectureDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Account writer;

    private String title;

    private long hits;

    private long likeNum;

    private boolean open;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "lecture")
    @OrderBy("seq")
    private List<LectureCard> cards = new ArrayList<>();

    @OneToMany(mappedBy = "lecture")
    private Set<LectureTag> tags = new HashSet<>();

    public Lecture(Account account, LectureDto dto) {
        this.writer = account;
        this.title = dto.getTitle();
        this.open = dto.isOpen();
        this.createdDate = LocalDateTime.now();
    }

    public void addCard(LectureCard card) {
        this.cards.add(card);
    }

    public void addTag(LectureTag tag) {
        this.tags.add(tag);
    }

}
