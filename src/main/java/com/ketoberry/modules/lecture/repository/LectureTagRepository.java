package com.ketoberry.modules.lecture.repository;

import com.ketoberry.modules.lecture.entity.LectureTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureTagRepository extends JpaRepository<LectureTag, Long> {
}
