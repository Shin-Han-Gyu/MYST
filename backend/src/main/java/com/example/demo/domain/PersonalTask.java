package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PersonalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private ProgressStatus progressStatus;

    @ManyToOne
    private User writer;

    @Builder
    public PersonalTask(String title, String content, ProgressStatus progressStatus, User writer) {
        this.title = title;
        this.content = content;
        this.progressStatus = progressStatus;
        this.writer = writer;
    }



}
