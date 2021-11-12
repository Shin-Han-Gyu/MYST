package com.example.demo.repository;

import com.example.demo.domain.GroupTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupTaskRepository extends JpaRepository<GroupTask, Long> {
}
