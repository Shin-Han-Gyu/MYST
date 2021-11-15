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
@Table(indexes = @Index(name="user_task", columnList = "writer"))
public class PersonalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User writer;

    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GroupTask groupTask;

    @Builder
    public PersonalTask(String title, User writer) {
        this.title = title;
        this.writer = writer;
    }



}
