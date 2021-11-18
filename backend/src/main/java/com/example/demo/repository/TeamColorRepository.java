package com.example.demo.repository;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamColor;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamColorRepository extends JpaRepository<TeamColor, Long> {
    Optional<TeamColor> findByTeamAndUser(Team team, User user);
    List<TeamColor> findAllByUserOrderByIdAsc(User user);
}
