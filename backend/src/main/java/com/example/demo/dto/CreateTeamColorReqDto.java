package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTeamColorReqDto {
    Long teamId;
    String colorCode;
}
