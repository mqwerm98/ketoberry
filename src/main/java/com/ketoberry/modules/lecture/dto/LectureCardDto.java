package com.ketoberry.modules.lecture.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LectureCardDto {

    @Length(min = 1, max = 100)
    private String content;

    private String image;

}
