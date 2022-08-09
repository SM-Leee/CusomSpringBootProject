package com.example.springtest.security.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {
    private String accessToken;
    private String refreshToken;

}