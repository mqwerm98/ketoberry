package com.ketoberry.modules.main;

import com.ketoberry.modules.account.repository.AccountRepository;
import com.ketoberry.modules.account.CurrentAccount;
import com.ketoberry.modules.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AccountRepository accountRepository;

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {
        if (account != null) {
            return "index";
        } else {
            return "index";
        }
    }
}
