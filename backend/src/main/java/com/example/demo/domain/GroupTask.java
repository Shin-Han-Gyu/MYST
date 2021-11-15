package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GroupTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private CompleteOption completeOption;

    @ManyToOne
    private User writer;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;

    @Builder
    public GroupTask(String title, CompleteOption completeOption, User writer, Group group) {
        this.title = title;
        this.completeOption = completeOption;
        this.writer = writer;
        this.group = group;
    }
}
