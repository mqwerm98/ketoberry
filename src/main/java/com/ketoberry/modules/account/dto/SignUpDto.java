package com.ketoberry.modules.account.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignUpDto {

    @NotBlank
    @Length(min = 2, max = 50)
    @Pattern(regexp = "^[가-힣a-z]{2,50}$")
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String nickname;

    private String password;
}
