package com.ketoberry.modules.lecture.controller;

import com.ketoberry.modules.account.CurrentAccount;
import com.ketoberry.modules.account.entity.Account;
import com.ketoberry.modules.lecture.dto.LectureDto;
import com.ketoberry.modules.lecture.entity.Lecture;
import com.ketoberry.modules.lecture.repository.LectureRepository;
import com.ketoberry.modules.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final LectureRepository lectureRepository;

    @GetMapping({"", "/list"})
    public String lectureList(Model model) {
        List<Lecture> list = lectureRepository.findAllByOpen(true);
        model.addAttribute("lectures", list);
        return "lecture/main";
    }

    @GetMapping("/form")
    public String lectureForm(@CurrentAccount Account account, Model model) {
        if (!account.isAdmin()) return "redirect:/";

        model.addAttribute(new LectureDto());
        return "lecture/form";
    }

    @PostMapping("")
    public String createLecture(@CurrentAccount Account account, @Valid LectureDto dto, Errors errors) {
        if (!account.isAdmin()) return "redirect:/";
        if (errors.hasErrors()) return "lecture/form";

        lectureService.createLecture(account, dto);

        return "redirect:/lecture";
    }
}
