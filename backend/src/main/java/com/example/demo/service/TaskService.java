package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.dto.TaskResDto;
import com.example.demo.dto.TeamColorResDto;
import com.example.demo.dto.TeamResDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.GroupJoinRepository;
import com.example.demo.repository.GroupTaskRepository;
import com.example.demo.repository.PersonalTaskRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final GroupTaskRepository groupTaskRepository;
    private final PersonalTaskRepository personalTaskRepository;
    private final UserRepository userRepository;
    private final GroupJoinRepository groupJoinRepository;

    private final GroupService groupService;

    @Transactional
    public boolean createPersonalTask(Long userId, String taskName) {
        User user = get_user(userId);
        if(user == null) return false;

        PersonalTask personalTask = PersonalTask.builder().title(taskName).user(user).build();
        personalTaskRepository.save(personalTask);
        return true;
    }

    @Transactional
    public boolean updateTodo(Long userId, Long taskId, String taskName) {
        User user = get_user(userId);
        if(user == null) return false;

        PersonalTask personalTask = get_task(taskId);
        if(personalTask == null) return false;

        GroupTask groupTask = personalTask.getGroupTask();

        // 개인 태스크
        if(groupTask == null) {
            personalTask.updateTask(taskName);
            personalTaskRepository.save(personalTask);
        }

        // 그룹 태스크
        else {
            groupTask.updateTask(taskName);
            groupTaskRepository.save(groupTask);
        }

        return true;
    }

    @Transactional
    public boolean deleteTodo(Long userId, Long taskId) {
        User user = get_user(userId);
        if(user == null) return false;

        PersonalTask personalTask = get_task(taskId);
        if(personalTask == null) return false;

        GroupTask groupTask = personalTask.getGroupTask();

        // 개인 태스크
        if(groupTask == null) {
            personalTaskRepository.delete(personalTask);
        }

        // 그룹 태스크
        else { groupTaskRepository.delete(groupTask); }

        return true;
    }

    @Transactional
    public boolean completeTodo(Long userId, Long taskId) {
        User user = get_user(userId);
        if(user == null) return false;

        PersonalTask personalTask = get_task(taskId);
        if(personalTask == null) return false;

        // 개인 태스크 완료상태 변경
        personalTask.toggleTask();
        personalTaskRepository.save(personalTask);

        return true;
    }

    @Transactional
    public void completeCheck(Long taskId) {
        PersonalTask personalTask = get_task(taskId);
        if(personalTask == null) return;

        GroupTask groupTask = personalTask.getGroupTask();

        // Optional 그룹태스크의 경우 완료여부 확인 후 완료 체크
        if(groupTask != null) {
            CompleteOption completeOption = groupTask.getCompleteOption();

            List<PersonalTask> personalTasks = personalTaskRepository.findByGroupTask(groupTask);

            boolean All_Done = true;
            boolean Someone_Done = false;

            for(PersonalTask task : personalTasks) {
                if(task.isComplete()) Someone_Done = true;
                else All_Done = false;
            }

            if(completeOption.equals(CompleteOption.DONE_BY_ALL)) {
                if(All_Done) groupTask.doneTask();
                else groupTask.notDoneTask();
                groupTaskRepository.save(groupTask);
            }

            if(completeOption.equals(CompleteOption.DONE_BY_ONE)) {
                if(Someone_Done) groupTask.doneTask();
                else groupTask.notDoneTask();
                groupTaskRepository.save(groupTask);
            }
        }

    }

    @Transactional(readOnly = true)
    public List<TaskResDto> readTodo(Long userId) {
        User user = get_user(userId);
        if(user == null) return null;

        List<PersonalTask> personalTasks = personalTaskRepository.findByUser(user);
        List<TaskResDto> taskResDtoList = new ArrayList<>();

        // 스레딩 가능?
        for(PersonalTask task : personalTasks) {
            GroupTask GT = task.getGroupTask();
            TaskResDto resDto;

            // 그룹 태스크일 경우
            if(GT != null) {
                Team team = GT.getTeam();
                String done = GT.isComplete() ? "Y" : "N";

                List<String> doneMember = new ArrayList<>();
                List<String> notYetMember = new ArrayList<>();

                List<PersonalTask> same_group_tasks = personalTaskRepository.findByGroupTask(GT);

                for(PersonalTask tt : same_group_tasks) {
                    String name = tt.getUser().getName();
                    if(tt.isComplete()) doneMember.add(name);
                    else notYetMember.add(name);
                }

                resDto = new TaskResDto(
                        task.getId(),
                        GT.getTitle(),
                        done,
                        doneMember,
                        notYetMember,
                        team.getId(),
                        team.getName(),
                        "#DDDDDD"
                );
            }


            // 개인태스크의 경우
            else {
                String Done = task.isComplete() ? "Y" : "N";
                resDto = new TaskResDto(task.getId(), task.getTitle(), Done);
            }

            taskResDtoList.add(resDto);
        }

        List<TeamColorResDto> teamColorResDtos = groupService.getTeamColor(userId);
        //컬러 세팅을 위한 정렬
        Comparator<TaskResDto> taskResDtoComparator = new Comparator<TaskResDto>() {
            @Override
            public int compare(TaskResDto d1, TaskResDto d2) {
                return (int)(d1.getTeamId() - d2.getTeamId());
            }
        };
        taskResDtoList.sort(taskResDtoComparator);
        colorSetting(taskResDtoList, teamColorResDtos);

        return taskResDtoList;
    }

    @Transactional
    public void todoAddedByMember(Long groupJoinId) {
        GroupJoin groupJoin = groupJoinRepository.findById(groupJoinId).orElseThrow(NotFoundException::new);

        User newMember = groupJoin.getUser();
        if(newMember == null) return;

        Team team = groupJoin.getTeam();

        List<GroupTask> tasks = groupTaskRepository.findByTeam(team);

        for(GroupTask tt : tasks) {
            if(!tt.isComplete()) {
                PersonalTask temp = new PersonalTask(tt.getTitle(), newMember, tt);
                personalTaskRepository.save(temp);
            }
        }
        return;
    }


    private void colorSetting(List<TaskResDto> taskResDtos, List<TeamColorResDto> teamColorResDtos){
        int i = 0, j = 0;
        while(i < teamColorResDtos.size() && j < taskResDtos.size()){
            if(taskResDtos.get(j).getTeamId() == -1){
                ++j; continue;
            }

            if(teamColorResDtos.get(i).getTeamId() == taskResDtos.get(j).getTeamId()){
                String newColor = teamColorResDtos.get(i).getColorCode();
                taskResDtos.get(j).changeColor(newColor);
                ++j;
            }
            else if(teamColorResDtos.get(i).getTeamId() > taskResDtos.get(j).getTeamId()){
                ++j;
            }
            else{
                ++i;
            }
        }
    }

    // 해당 태스크가 유저가 속한 것인지 체크할 것!
    private PersonalTask get_task(Long taskId) { return personalTaskRepository.findById(taskId).orElse(null); }
    private User get_user(Long userId) { return userRepository.findById(userId).orElse(null); }
}
