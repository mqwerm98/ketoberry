package com.ketoberry.modules.account.validator;

import com.ketoberry.modules.account.repository.AccountRepository;
import com.ketoberry.modules.account.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpDtoValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpDto signUpDto = (SignUpDto) o;
        if (accountRepository.existsByEmail(signUpDto.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpDto.getEmail()}, "이미 사용중인 이메일입니다.");
        }

        if (accountRepository.existsByNickname(signUpDto.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpDto.getNickname()}, "이미 사용중인 닉네임입니다.");
        }
    }
}
