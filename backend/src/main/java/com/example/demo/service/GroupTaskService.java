package com.example.demo.service;

import com.example.demo.dto.TaskResDto;
import com.example.demo.repository.GroupTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupTaskService {

    private final GroupTaskRepository groupTaskRepository;

    @Transactional
    public boolean createGroupTask(Long userId, String taskName) {
        // 그룹 태스크 만들고 (옵션 빼먹지말고)
        // 해당되는 멤버들에 대해 각각의 태스크 만들고
        return true;
    }

    @Transactional(readOnly = true)
    public List<TaskResDto> readTodo(Long userId, Long groupId) {
        // 그룹 아이디로 조회해서
        // 해당그룹에 있는 Todo 리스트로 반환
        return null;
    }

    // 해당 태스크가 유저가 속한 것인지 체크할 것!
}
