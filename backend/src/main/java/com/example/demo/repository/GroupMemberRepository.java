package com.example.demo.repository;

import com.example.demo.domain.Team;
import com.example.demo.domain.GroupMember;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findByTeam(Team team);

    Optional<GroupMember> findByTeamAndUser(Team team, User user);
    int countByTeamAndUser(Team team, User user);
}
