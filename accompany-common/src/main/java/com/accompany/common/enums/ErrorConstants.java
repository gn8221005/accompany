package com.accompany.common.enums;


import lombok.Getter;

@Getter
public enum ErrorConstants {


    COURSE_CODE_IS_NULL(1500, "课程编码为空"),
    COURSE_NAME_HAS_EXIST(1501, "课程名称已经存在"),
    COURSE_IS_NOT_EXIST(1502, "课程不存在");


    private final Integer code;

    private final String message;


    ErrorConstants(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
