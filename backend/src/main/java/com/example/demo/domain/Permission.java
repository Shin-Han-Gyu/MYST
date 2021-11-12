package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // db에 row가 있다는게 허가 했다는거니까 없어도 될듯?
    // private boolean isChecked;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    private User user;

    @ManyToOne
    private GroupTask task;

    @Builder
    public Permission(User user, GroupTask task, Type type) {
        this.user = user;
        this.task = task;
        this.type = type;
    }
}
