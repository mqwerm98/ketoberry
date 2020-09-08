package com.ketoberry.modules.lecture.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class LectureDto {

    @NotBlank
    @Length(min = 1, max = 100)
    private String title;

    private boolean open = false;

    private String content;

    private Set<String> tags = new HashSet<>();

}
