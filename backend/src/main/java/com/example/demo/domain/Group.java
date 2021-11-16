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
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    @OneToMany(mappedBy = "group")
    private List<GroupJoin> groupJoins = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<GroupMember> groupMembers = new ArrayList<>();

    @Builder
    public Group(String name, String content) {
        this.name = name;
        this.content = content;
    }

}
