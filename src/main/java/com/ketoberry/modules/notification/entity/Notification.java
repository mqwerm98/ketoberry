package com.ketoberry.modules.notification.entity;

import com.ketoberry.modules.account.entity.Account;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Notification {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String title;

    private String message;

    private boolean checked;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private LocalDateTime createdDate;

}
