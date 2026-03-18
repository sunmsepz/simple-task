package com.example.simple.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    /** Http 상태 코드 */
    private final int code;

    /** Api Message */
    private final String msg;

    /** Api 응답 데이터 */
    private final T data;

    /**
     * Api 응답 성공
     */
    public static <T> ApiResponse<T> success(int code, String msg, T data) {
        return new ApiResponse<>(code, msg, data);
    }

    /**
     * Api 응답 실패
     */
    public static <T> ApiResponse<T> error(int code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }
}
