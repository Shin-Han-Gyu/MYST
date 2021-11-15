package com.example.demo.service;

import com.example.demo.dto.TaskResDto;
import com.example.demo.repository.PersonalTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final PersonalTaskRepository personalTaskRepository;

    @Transactional
    public boolean createPersonalTask(Long userId, String taskName) {
        // 개인 태스크 만들기
        return true;
    }

    @Transactional
    public boolean updateTodo(Long userId, Long taskId, String taskName) {
        // 개인 태스크 수정
        // or
        // 그룹 태스크 수정 및 해당 개인태스크 모두 수정
        return true;
    }

    @Transactional
    public boolean deleteTodo(Long userId, Long taskId) {
        // 그룹 태스크 삭제(CASCADE 옵션 활용 개인태스크 자동 삭제)
        // or
        // 개인 태스크 삭제
        return true;
    }

    @Transactional
    public boolean completeTodo(Long userId, Long taskId) {
        // 개인 태스크 완료
        // Optional 그룹태스크의 경우 완료여부 확인 후 완료 체크
        return true;
    }

    @Transactional(readOnly = true)
    public List<TaskResDto> readTodo(Long userId) {
        return null;
    }

    // 해당 태스크가 유저가 속한 것인지 체크할 것!
}
