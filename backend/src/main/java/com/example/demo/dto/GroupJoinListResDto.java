package com.example.demo.dto;

import com.example.demo.domain.GroupJoin;
import lombok.Getter;

@Getter
public class GroupJoinListResDto {

    private Long groupJoinId;
    private String userId;

    public GroupJoinListResDto(GroupJoin groupJoin) {
        this.groupJoinId = groupJoin.getId();
        this.userId = groupJoin.getUser().getUserId();
    }
}
