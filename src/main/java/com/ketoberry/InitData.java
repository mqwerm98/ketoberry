package com.ketoberry;

import com.ketoberry.modules.account.dto.SignUpDto;
import com.ketoberry.modules.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

//    @PostConstruct
//    public void init() {
//        initService.createRoomUser();
//    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;

        public void createRoomUser() {
            SignUpDto dto = new SignUpDto();
            dto.setName("김베리");
            dto.setEmail("mqwerm98@naver.com");
            dto.setNickname("ketoberry");
            dto.setPassword(passwordEncoder.encode("12341234"));

            Account account = new Account(dto);
            account.generateEmailCheckToken();
            account.completeSignUp();

            em.persist(account);
        }

    }
}
