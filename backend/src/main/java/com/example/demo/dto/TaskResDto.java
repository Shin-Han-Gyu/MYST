package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TaskResDto {

    private String taskName;
    private String taskDone;
    private List<String> done;
    private List<String> notYet;
    private String teamId;
    private String teamName;
}
