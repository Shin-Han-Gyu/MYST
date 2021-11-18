package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.dto.*;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final GroupJoinRepository groupJoinRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final TeamColorRepository teamColorRepository;

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
        List<TeamColorResDto> teamColorResDtos = getTeamColor(userId);
        List<TeamResDto> teamResDtos = user.getGroupMembers().stream().map(TeamResDto::new).collect(Collectors.toList());

        Comparator<TeamResDto> teamResDtoComparator = new Comparator<TeamResDto>() {
            @Override
            public int compare(TeamResDto d1, TeamResDto d2) {
                return (int)(d1.getId() - d2.getId());
            }
        };
        teamResDtos.sort(teamResDtoComparator);
        colorSetting(teamResDtos, teamColorResDtos);

        return teamResDtos;
    }

    public void createJoin(Long userId, Long teamId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Team team = teamRepository.findById(teamId).orElseThrow(NotFoundException::new);

        if(groupJoinRepository.countByTeamAndUser(team, user) != 0) return ;

        GroupJoin groupJoin = GroupJoin.builder()
                                .team(team)
                                .user(user)
                                .isAccepted(false)
                                .build();
        groupJoinRepository.save(groupJoin);
    }

    @Transactional
    public void acceptJoin(Long groupJoinId, Long leaderId) throws IllegalAccessException {
        GroupJoin groupJoin = groupJoinRepository.findById(groupJoinId).orElseThrow(NotFoundException::new);
        if(!isLeader(groupJoin.getTeam(), leaderId))
            throw new IllegalAccessException("승인 권한이 없습니다.");

        groupJoin.acceptJoin();
        groupJoinRepository.save(groupJoin);

        GroupMember newMember = GroupMember.builder()
                                    .team(groupJoin.getTeam())
                                    .user(groupJoin.getUser())
                                    .position(Position.Member)
                                    .build();
        groupMemberRepository.save(newMember);

    }

    @Transactional
    public void deleteJoin(Long groupJoinId, Long leaderId) throws IllegalAccessException {
        GroupJoin groupJoin = groupJoinRepository.findById(groupJoinId).orElseThrow(NotFoundException::new);
        if(!isLeader(groupJoin.getTeam(), leaderId))
            throw new IllegalAccessException("삭제 권한이 없습니다.");

        groupJoinRepository.delete(groupJoin);
    }

    @Transactional
    public List<GroupJoinListResDto> getJoinList(Long teamId, Long leaderId) throws IllegalAccessException{
        Team team = teamRepository.findById(teamId).orElseThrow(NotFoundException::new);

        if(!isLeader(team, leaderId))
            throw new IllegalAccessException("권한이 없습니다.");

        return groupJoinRepository.findAllByTeamAndIsAcceptedIsFalse(team)
                .stream().map(GroupJoinListResDto::new).collect(Collectors.toList());
    }

    public void setTeamColor(Long userId, CreateTeamColorReqDto colorReqDto) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Team team = teamRepository.findById(colorReqDto.getTeamId()).orElseThrow(NotFoundException::new);
        TeamColor teamColor = teamColorRepository.findByTeamAndUser(team, user).orElse(
                    TeamColor.builder()
                            .team(team)
                            .user(user)
                            .build()
            );
        teamColor.changeColor(colorReqDto.getColorCode());
        teamColorRepository.save(teamColor);
    }

    public List<TeamColorResDto> getTeamColor(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return teamColorRepository.findAllByUserOrderByIdAsc(user)
                .stream().map(TeamColorResDto::new).collect(Collectors.toList());
    }

    private boolean isLeader(Team team, Long leaderId){
        User leader = userRepository.findById(leaderId).orElseThrow(NotFoundException::new);
        GroupMember groupLeader = groupMemberRepository.findByTeamAndUser(team, leader).orElseThrow(NotFoundException::new);

        if(groupLeader.getPosition() != Position.Leader)
            return false;
        return true;
    }

    private void colorSetting(List<TeamResDto> teamResDtos, List<TeamColorResDto> teamColorResDtos){
        int i = 0, j = 0;
        while(i < teamColorResDtos.size() && j < teamResDtos.size()){
            if(teamColorResDtos.get(i).getTeamId() == teamResDtos.get(j).getId()){
                String newColor = teamColorResDtos.get(i).getColorCode();
                teamResDtos.get(j).changeColor(newColor);
                ++i;++j;
            }
            else if(teamColorResDtos.get(i).getTeamId() > teamResDtos.get(j).getId()){
                ++j;
            }
            else{
                ++i;
            }
        }
    }
}
