package com.example.demo.dto;


import com.example.demo.domain.GroupMember;
import com.example.demo.domain.Team;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class GroupDetailResDto implements Serializable {
    private String name;
    private String content;
    private List<GroupMemberDto> groupMemberDtos ;

    public GroupDetailResDto(Team team) {
        this.name = team.getName();
        this.content = team.getContent();
        this.groupMemberDtos = team.getGroupMembers().stream().map(GroupMemberDto::new).collect(Collectors.toList());
    }

}
