package com.ketoberry.modules.lecture.repository;

import com.ketoberry.modules.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByOpen(boolean open);
}
