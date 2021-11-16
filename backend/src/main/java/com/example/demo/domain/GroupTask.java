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

    private boolean isComplete;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public GroupTask(String title, CompleteOption completeOption, Group group) {
        this.title = title;
        this.completeOption = completeOption;
        this.group = group;
    }

    public void updateTask(String title) {
        this.title = title;
    }

    public void doneTask() {
        this.isComplete = true;
    }

    public void notDoneTask() {
        this.isComplete = false;
    }
}
