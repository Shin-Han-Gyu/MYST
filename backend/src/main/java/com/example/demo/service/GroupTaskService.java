package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.dto.TaskReqDto;
import com.example.demo.dto.TaskResDto;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupTaskService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupTaskRepository groupTaskRepository;
    private final PersonalTaskRepository personalTaskRepository;
    private final UserRepository userRepository;

    @Transactional
    public boolean createGroupTask(Long userId, Long groupId, TaskReqDto reqDto) {
        User user = get_user(userId);
        if(user == null) return false;

        Group group = get_group(groupId);
        if(group == null) return false;

        CompleteOption option = reqDto.getCompleteOption().equals("ALL") ? CompleteOption.DONE_BY_ALL : CompleteOption.DONE_BY_ONE;

        // 그룹 태스크 만들고 (옵션 빼먹지말고)
        GroupTask groupTask = GroupTask.builder().title(reqDto.getTaskName()).completeOption(option).group(group).build();
        groupTaskRepository.save(groupTask);

        // 해당되는 멤버들에 대해 각각의 개인 태스크 만들고
        List<GroupMember> members = groupMemberRepository.findByGroup(group);
        for(GroupMember member : members) {
            PersonalTask personalTask = PersonalTask.builder().user(member.getUser()).groupTask(groupTask).build();
            personalTaskRepository.save(personalTask);
        }

        return true;
    }

    @Transactional(readOnly = true)
    public List<TaskResDto> readTodo(Long userId, Long groupId) {
        User user = get_user(userId);
        if(user == null) return null;

        Group group = get_group(groupId);
        if(group == null) return null;

        // 그룹 아이디로 조회해서
        List<GroupTask> groupTasks = groupTaskRepository.findByGroup(group);
        if(groupTasks.isEmpty()) return null;

        List<TaskResDto> resDtos = new ArrayList<>();

        // 해당그룹에 있는 Todo들을 리스트로 반환
        for(GroupTask task : groupTasks) {
            String done = task.isComplete() ? "Y" : "N";

            List<String> doneMember = new ArrayList<>();
            List<String> notYetMember = new ArrayList<>();

            List<PersonalTask> same_group_tasks = personalTaskRepository.findByGroupTask(task);

            for(PersonalTask tt : same_group_tasks) {
                String name = tt.getUser().getName();
                if(tt.isComplete()) doneMember.add(name);
                else notYetMember.add(name);
            }

            TaskResDto dto = new TaskResDto(
                    task.getId(),
                    task.getTitle(),
                    done,
                    doneMember,
                    notYetMember
            );

            resDtos.add(dto);
        }

        return resDtos;
    }

    // 해당 태스크가 유저가 속한 것인지 체크할 것!
    private Group get_group(Long groupId) { return groupRepository.findById(groupId).orElse(null); }
    private User get_user(Long userId) { return userRepository.findById(userId).orElse(null); }
}
