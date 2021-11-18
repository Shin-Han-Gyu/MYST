package com.example.demo.dto;

import com.example.demo.domain.TeamColor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamColorResDto {
    Long teamId;
    String colorCode;

    public TeamColorResDto(TeamColor teamColor){
        this.teamId = teamColor.getTeam().getId();
        this.colorCode = teamColor.getColorCode();
    }
}
