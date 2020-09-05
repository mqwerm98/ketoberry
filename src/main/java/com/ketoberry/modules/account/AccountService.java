package com.ketoberry.modules.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ketoberry.infra.config.AppProperties;
import com.ketoberry.infra.mail.EmailService;
import com.ketoberry.infra.mail.Emailmessage;
import com.ketoberry.modules.account.dto.SignUpDto;
import com.ketoberry.modules.account.entity.Account;
import com.ketoberry.modules.account.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppProperties appProperties;
    private final TemplateEngine templateEngine;
    private final EmailService emailService;

    public Account signUp(SignUpDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Account account = new Account(dto);
        account.generateEmailCheckToken();

        Account newAccount = accountRepository.save(account);

        sendSignUpConfirmEmail(newAccount);
        return newAccount;
    }

    void sendSignUpConfirmEmail(Account account) {
        Context context = new Context();
        context.setVariable("link", "/check-email-token?token=" + account.getEmailToken() + "&email=" + account.getEmail());
        context.setVariable("nickname", account.getNickname());
        context.setVariable("linkName", "이메일 인증하기");
        context.setVariable("message", "키토베리 서비스를 사용하려면 링크를 클릭하세요");
        context.setVariable("host", appProperties.getHost());

        String message = templateEngine.process("mail/simple-link", context);

        Emailmessage emailmessage = Emailmessage.builder()
                .to(account.getEmail())
                .subject("키토베리, 회원 가입 인증")
                .message(message)
                .build();

        emailService.sendEmail(emailmessage);
    }

    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public void completeSignUp(Account account) {
        account.completeSignUp();
        login(account);
    }

    public void sendLoginLink(Account account) {
        account.generateEmailCheckToken();

        Context context = new Context();
        context.setVariable("link", "/login-by-email?token=" + account.getEmailToken() + "&email=" + account.getEmail());
        context.setVariable("nickname", account.getNickname());
        context.setVariable("linkName", "키토베리 로그인하기");
        context.setVariable("message", "로그인 하려면 아래 링크를 클릭하세요");
        context.setVariable("host", appProperties.getHost());

        String message = templateEngine.process("mail/simple-link", context);

        Emailmessage emailmessage = Emailmessage.builder()
                .to(account.getEmail())
                .subject("키토베리, 로그인 링크")
                .message(message)
                .build();

        emailService.sendEmail(emailmessage);
    }

    public Account getAccount(String nickname) {
        Account account = accountRepository.findByNickname(nickname);
        if (account == null) {
            throw new IllegalArgumentException(nickname + "에 해당하는 사용자가 없습니다");
        }
        return account;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserAccount(account);
    }
}
