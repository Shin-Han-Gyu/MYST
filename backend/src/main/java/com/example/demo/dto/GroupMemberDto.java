package com.example.demo.dto;

import com.example.demo.domain.GroupMember;
import com.example.demo.domain.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Getter
public class GroupMemberDto implements Serializable {
    private Long id;
    private String userId;
    private String userName;

    public GroupMemberDto(GroupMember groupMember) {
        User user = groupMember.getUser();

        this.id = user.getId();
        this.userId = user.getUserId();
        this.userName = user.getName();
    }
}
