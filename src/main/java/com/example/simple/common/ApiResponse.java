package com.example.simple.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;

/**
 * Api 응답 클래스
 *
 * @param <T> 응답 데이터 타입
 */
@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    /** Http 상태 코드 */
    private final int code;

    /** Api Message */
    @NotNull
    private final String msg;

    /** Api 응답 데이터 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    /** Api 응답 에러 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final ProblemDetail error;

    /**
     * API 응답 성공
     *
     * @param code Http 상태 코드
     * @param msg Api 응답 메시지
     * @param data 응답 데이터
     *
     * @return Api 응답 성공 메시지
     */
    public static <T> ApiResponse<T> success(int code, String msg, T data) {
        return new ApiResponse<>(code, msg, data, null);
    }

    /**
     * API 응답 에러
     *
     * @param code Http 상태 코드
     * @param msg Api 응답 메시지
     * @param error Api 응답 에러 상세내용
     *
     * @return Api 응답 에러 메시지
     */
    public static <T> ApiResponse<T> error(int code, String msg, ProblemDetail error) {
        return new ApiResponse<>(code, msg, null, error);
    }
}
