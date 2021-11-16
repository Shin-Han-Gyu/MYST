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
@Table(indexes = {@Index(columnList = "writer"), @Index(columnList = "groupTask")})
public class PersonalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 그룹 태스크의 경우 그룹 태스크의 title을 활용
    private String title;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User writer;

    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GroupTask groupTask;

    // 각 개인 완료여부 표시이며
    // 그룹 태스크의 경우 그룹 태스크의 isComplete를 활용
    private boolean isComplete;

    @Builder
    public PersonalTask(String title, User writer, GroupTask groupTask) {
        this.title = title;
        this.writer = writer;
        this.groupTask = groupTask;
    }

    @Builder
    public PersonalTask(String title, User writer) {
        this.title = title;
        this.writer = writer;
    }

    public void updateTask(String title) {
        this.title = title;
    }

    public void toggleTask() {
        this.isComplete = !this.isComplete;
    }
}
