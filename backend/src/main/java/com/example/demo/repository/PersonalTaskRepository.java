package com.example.demo.repository;

import com.example.demo.domain.GroupTask;
import com.example.demo.domain.PersonalTask;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalTaskRepository extends JpaRepository<PersonalTask, Long> {
    List<PersonalTask> findByUser(User user);
    List<PersonalTask> findByGroupTask(GroupTask groupTask);
}
