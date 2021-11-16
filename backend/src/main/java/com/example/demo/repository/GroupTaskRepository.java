package com.example.demo.repository;

import com.example.demo.domain.Group;
import com.example.demo.domain.GroupTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupTaskRepository extends JpaRepository<GroupTask, Long> {
    List<GroupTask> findByGroup(Group group);
}
