package com.example.demo.dto;

import com.example.demo.domain.GroupMember;
import com.example.demo.domain.Team;
import lombok.Getter;

@Getter
public class TeamResDto {
    Long id;
    String name;
    String content;
    String colorCode;

    public TeamResDto(GroupMember groupMember) {
        Team team = groupMember.getTeam();

        this.id = team.getId();
        this.name = team.getName();
        this.content = team.getContent();
        this.colorCode = "#DDDDDD"; //default color
    }
    public void changeColor(String colorCode){
        this.colorCode = colorCode;
    }

}
