package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @UniqueElements
    private String userId;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "user")
    private List<GroupTask> groupTasks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PersonalTask> personalTasks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<GroupJoin> groupJoins = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<GroupMember> groupMembers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TeamColor>  teamColors = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Builder
    public User(String name, String userId, String password) {
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
