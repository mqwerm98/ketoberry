package com.ketoberry.modules.account.entity;

import com.ketoberry.modules.account.dto.SignUpDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailToken;

    private LocalDateTime emailTokenGeneratedDate;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private AccountGrade grade;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    public Account(SignUpDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.grade = AccountGrade.STRAW;
        this.type = AccountType.USER;
    }

    public void generateEmailCheckToken() {
        this.emailToken = UUID.randomUUID().toString();
        this.emailTokenGeneratedDate = LocalDateTime.now();
    }

    public void completeSignUp() {
        this.emailVerified = true;
        this.createdDate = LocalDateTime.now();
    }

    public boolean isValidToken(String token) {
        return this.emailToken.equals(token);
    }

    public boolean canSendConfirmEmail() {
        return this.emailTokenGeneratedDate.isBefore(LocalDateTime.now().minusMinutes(3));
    }

    public boolean isAdmin() {
        return this.type == AccountType.ADMIN;
    }
}
