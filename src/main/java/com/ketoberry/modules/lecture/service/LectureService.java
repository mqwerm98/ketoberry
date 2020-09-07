package com.ketoberry.modules.lecture.service;

import com.ketoberry.modules.Tag.TagService;
import com.ketoberry.modules.Tag.entity.Tag;
import com.ketoberry.modules.account.entity.Account;
import com.ketoberry.modules.lecture.dto.LectureCardDto;
import com.ketoberry.modules.lecture.dto.LectureDto;
import com.ketoberry.modules.lecture.entity.Lecture;
import com.ketoberry.modules.lecture.entity.LectureCard;
import com.ketoberry.modules.lecture.entity.LectureTag;
import com.ketoberry.modules.lecture.repository.LectureCardRepository;
import com.ketoberry.modules.lecture.repository.LectureRepository;
import com.ketoberry.modules.lecture.repository.LectureTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureCardRepository lectureCardRepository;
    private final LectureTagRepository lectureTagRepository;
    private final TagService tagService;

    public Lecture createLecture(Account account, LectureDto dto) {
        Lecture lecture = new Lecture(account, dto);
        lectureRepository.save(lecture);

        int seq = 1;
        for (LectureCardDto card : dto.getCards()) {
            LectureCard lectureCard = new LectureCard(lecture, card, seq);
            lectureCardRepository.save(lectureCard);
            lecture.addCard(lectureCard);
            seq++;
        }

        for (String tagName : dto.getTags()) {
            Tag tag = tagService.findOrCreateNew(tagName);
            LectureTag lectureTag = new LectureTag(lecture, tag);
            lectureTagRepository.save(lectureTag);
        }

        return lecture;
    }
}
