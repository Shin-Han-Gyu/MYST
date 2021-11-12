package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GroupTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private CompleteOption completeOption;

    @Enumerated(EnumType.STRING)
    private DeleteOption deleteOption;

    @Enumerated(EnumType.STRING)
    private ProgressStatus progressStatus;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Group group;

    @Builder
    public GroupTask(String title, String content, CompleteOption completeOption, DeleteOption deleteOption, ProgressStatus progressStatus, User writer, Group group) {
        this.title = title;
        this.content = content;
        this.completeOption = completeOption;
        this.deleteOption = deleteOption;
        this.progressStatus = progressStatus;
        this.writer = writer;
        this.group = group;
    }
}
