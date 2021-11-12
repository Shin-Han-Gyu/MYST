package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProgressStatus {
    WAITING, IN_PROGRESS, COMPLETE
}
