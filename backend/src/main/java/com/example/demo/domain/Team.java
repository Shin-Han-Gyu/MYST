package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    @OneToMany(mappedBy = "team")
    private List<GroupJoin> groupJoins = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<GroupMember> groupMembers = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<TeamColor>  teamColors = new ArrayList<>();


    @Builder
    public Team(String name, String content) {
        this.name = name;
        this.content = content;
    }

}
