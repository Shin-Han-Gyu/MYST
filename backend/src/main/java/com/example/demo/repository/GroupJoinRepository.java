package com.example.demo.repository;

import com.example.demo.domain.GroupJoin;
import com.example.demo.domain.Team;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupJoinRepository extends JpaRepository<GroupJoin, Long> {
    int countByTeamAndUser(Team team, User user);
    Optional<GroupJoin> findByTeamAndUser(Team team, User user);
}
