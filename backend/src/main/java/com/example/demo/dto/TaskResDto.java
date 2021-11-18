package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TaskResDto {

    private Long taskId;
    private String taskName;
    private String taskDone;
    private List<String> done;
    private List<String> notYet;
    private Long teamId;
    private String teamName;
    private String colorCode;

    public TaskResDto(Long taskId, String taskName, String taskDone) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDone = taskDone;
        this.done = null;
        this.notYet = null;
        this.teamId = Long.valueOf(-1);
        this.teamName = "";
        this.colorCode = "#DDDDDD";
    }

    public TaskResDto(Long taskId, String taskName, String taskDone, List<String> done, List<String> notYet) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDone = taskDone;
        this.done = done;
        this.notYet = notYet;
        this.teamId = Long.valueOf(-1);
        this.teamName = "";
        this.colorCode = "#DDDDDD";
    }

    public void changeColor(String colorCode){
        this.colorCode = colorCode;
    }
}
