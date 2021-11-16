package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.domain.GroupMember;
import com.example.demo.domain.Position;
import com.example.demo.domain.User;
import com.example.demo.dto.CreateGroupReqDto;
import com.example.demo.dto.GroupDetailResDto;
import com.example.demo.dto.TeamResDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.GroupJoinRepository;
import com.example.demo.repository.GroupMemberRepository;
import com.example.demo.repository.TeamRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final GroupJoinRepository groupJoinRepository;
    private final GroupMemberRepository groupMemberRepository;

    public void createGroup(Long userId, CreateGroupReqDto createGroupReqDto) {
        Team team = createGroupReqDto.toEntity();
        teamRepository.save(team);

        User leader = userRepository.findById(userId).orElseThrow(NotFoundException::new) ;
        GroupMember groupMember = GroupMember.builder()
                                    .team(team)
                                    .user(leader)
                                    .position(Position.Leader)
                                    .build();
        groupMemberRepository.save(groupMember);
    }

    @Transactional
    public GroupDetailResDto getGroupDetails( Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(NotFoundException::new);
        return new GroupDetailResDto(team);
    }

    @Transactional
    public List<TeamResDto> getGroupList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return user.getGroupMembers().stream().map(TeamResDto::new).collect(Collectors.toList());
    }
}
