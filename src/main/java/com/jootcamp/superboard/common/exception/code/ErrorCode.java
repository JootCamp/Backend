package com.jootcamp.superboard.common.exception.code;

import lombok.Getter;

@Getter
public enum ErrorCode {


    // 잘못된 서버 요청
    BAD_REQUEST_ERROR(400,  "잘못된 서버 요청"),

    // @RequestBody 데이터 미 존재
    REQUEST_BODY_MISSING_ERROR(400,  "@RequestBody 데이터가 존재하지 않음"),

    // 유효하지 않은 타입
    INVALID_TYPE_VALUE(400,  "유효하지 않은 타입"),

    // Request Parameter 로 데이터가 전달되지 않을 경우
    MISSING_REQUEST_PARAMETER_ERROR(400,  "Request Parameter 데이터가 존재하지 않음"),

    // 입력/출력 값이 유효하지 않음
    IO_ERROR(400,  "입력/출력 값이 유효하지 않음"),

    // 권한이 없음
    FORBIDDEN_ERROR(403,  "Forbidden Exception"),

    // 서버로 요청한 리소스가 존재하지 않음
    NOT_FOUND_ERROR(404,  "Not Found Exception"),

    // 서버로 요청한 게시판이 존재하지 않음
    NOT_FOUND_BOARD_ERROR(404,  "해당하는 보드를 찾지 못하였습니다."),

    // NULL Point Exception 발생
    NULL_POINT_ERROR(404,  "Null Point Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_ERROR(404,  "handle Validation Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_HEADER_ERROR(404,  "Header에 데이터가 존재하지 않는 경우 "),

    // 서버가 처리 할 방법을 모르는 경우 발생
    INTERNAL_SERVER_ERROR(500, "Internal Server Error Exception");

    private final int status; // 상태 코드
    private final String meg; //에러 메세지

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.meg = message;
    }

}
