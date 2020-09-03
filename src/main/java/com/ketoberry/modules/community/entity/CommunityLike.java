package com.ketoberry.modules.community.entity;

import com.ketoberry.modules.account.entity.Account;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CommunityLike {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}
