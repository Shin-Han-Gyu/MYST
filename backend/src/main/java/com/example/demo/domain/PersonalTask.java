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
//@Table(indexes = {@Index(columnList = "writer"), @Index(columnList = "groupTask")})
public class PersonalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 그룹 태스크의 경우 그룹 태스크의 title을 활용
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GroupTask groupTask;

    // 각 개인 완료여부 표시이며
    // 그룹 태스크의 경우 그룹 태스크의 isComplete를 활용
    private boolean isComplete;

    @Builder
    public PersonalTask(String title, User user, GroupTask groupTask) {
        this.title = title;
        this.user = user;
        this.groupTask = groupTask;
    }

    @Builder
    public PersonalTask(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public void updateTask(String title) {
        this.title = title;
    }

    public void toggleTask() {
        this.isComplete = !this.isComplete;
    }
}
