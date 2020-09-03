package com.ketoberry.modules.community.entity;

import com.ketoberry.modules.Tag.entity.Tag;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CommunityTag {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
