package com.example.demo.service;

import com.example.demo.domain.Group;
import com.example.demo.domain.GroupMember;
import com.example.demo.domain.Position;
import com.example.demo.domain.User;
import com.example.demo.dto.CreateGroupReqDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.GroupJoinRepository;
import com.example.demo.repository.GroupMemberRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupJoinRepository groupJoinRepository;
    private final GroupMemberRepository groupMemberRepository;

    public void createGroup(Long userId, CreateGroupReqDto createGroupReqDto) {
        Group group = createGroupReqDto.toEntity();
        groupRepository.save(group);

        User leader = userRepository.findById(userId).orElseThrow(NotFoundException::new) ;
        GroupMember groupMember = GroupMember.builder()
                                    .group(group)
                                    .user(leader)
                                    .position(Position.Leader)
                                    .build();
        groupMemberRepository.save(groupMember);
    }

}
