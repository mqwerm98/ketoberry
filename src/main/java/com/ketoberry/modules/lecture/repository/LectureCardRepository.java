package com.ketoberry.modules.lecture.repository;

import com.ketoberry.modules.lecture.entity.LectureCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureCardRepository extends JpaRepository<LectureCard, Long> {
}
