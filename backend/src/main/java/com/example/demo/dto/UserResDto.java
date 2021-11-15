package com.example.demo.dto;

import com.example.demo.domain.GroupTask;
import com.example.demo.domain.PersonalTask;
import com.example.demo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserResDto {

    private String name;
    private String email;
    private List<GroupTask> groupTasks;
    private List<PersonalTask> personalTasks;

    public static UserResDto of(User user) {
        UserResDto userResDto = new UserResDto();
        userResDto.name = user.getName();
        userResDto.email = user.getEmail();
//        userResDto.groupTasks = user.getGroupTasks();
//        userResDto.personalTasks = user.getPersonalTasks();
        return userResDto;
    }
}
