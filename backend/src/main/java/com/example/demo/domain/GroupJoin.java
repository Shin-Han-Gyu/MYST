package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
public class GroupJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ColumnDefault("false")
    private Boolean isAccepted;

    @Builder
    public GroupJoin(Team team, User user, Boolean isAccepted) {
        this.team = team;
        this.user = user;
        this.isAccepted = isAccepted;
    }
}
