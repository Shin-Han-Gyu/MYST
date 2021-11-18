package com.example.demo.dto;

import com.example.demo.domain.GroupMember;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class TeamPositionResDto {
    Long userId;
    Long teamId;
    String position;

    public TeamPositionResDto(Long userId, Long teamId, String position) {
        this.userId = userId;
        this.teamId = teamId;
        this.position = position;
    }

    public TeamPositionResDto(GroupMember groupMember) {
        this.userId = groupMember.getUser().getId();
        this.teamId = groupMember.getTeam().getId();
        this.position = groupMember.getPosition().toString();
    }
}
