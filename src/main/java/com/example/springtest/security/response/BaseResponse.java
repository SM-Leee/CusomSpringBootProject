package com.example.springtest.security.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private boolean success; // 요청 성공 여부
    private String message; // 응답 메세지
    private String errorCode; // 1 : 아이디 잘못 / 2 : 비밀번호 잘못
}
