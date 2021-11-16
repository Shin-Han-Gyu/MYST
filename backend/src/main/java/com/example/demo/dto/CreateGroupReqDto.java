package com.example.demo.dto;

import com.example.demo.domain.Group;
import lombok.Getter;

@Getter
public class CreateGroupReqDto {
    private String name;
    private String content;

    public Group toEntity(){
        return Group.builder()
                .name(this.name)
                .content(this.content)
                .build();
    }
}
