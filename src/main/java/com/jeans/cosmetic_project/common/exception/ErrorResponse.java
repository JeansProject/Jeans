package com.jeans.cosmetic_project.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String code; // HTTP 상태 코드
    private final String message;

    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
