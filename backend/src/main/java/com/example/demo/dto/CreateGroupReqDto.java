package com.example.demo.dto;

import com.example.demo.domain.Team;
import lombok.Getter;

@Getter
public class CreateGroupReqDto {
    private String name;
    private String content;

    public Team toEntity(){
        return Team.builder()
                .name(this.name)
                .content(this.content)
                .build();
    }
}
