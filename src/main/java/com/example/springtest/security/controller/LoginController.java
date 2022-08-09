package com.example.springtest.security.controller;

import com.example.springtest.security.jwt.TokenProvider;
import com.example.springtest.security.model.LoginModel;
import com.example.springtest.security.model.TokenModel;
import com.example.springtest.security.response.BaseResponse;
import com.example.springtest.security.response.SingleDataResponse;
import com.example.springtest.security.service.ResponseService;
import com.example.springtest.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    ResponseService responseService;
    @Autowired
    TokenProvider tokenProvider;

    @RequestMapping("/Admin")
    public String Admin(){
        System.out.println("123123123");
        return "Admin";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginModel loginDto) {
        ResponseEntity responseEntity = null;
        try {
            String userId = userService.login(loginDto);
            TokenModel token = userService.tokenGenerator(userId);
            ResponseCookie responseCookie =
                ResponseCookie.from(HttpHeaders.SET_COOKIE, token.getRefreshToken())///new Cookie("refreshToken", token.getRefreshToken());
                .path("/")
                .maxAge(14 * 24 * 60 * 60) // 14일
                .httpOnly(true)
                // .secure(true)
                .build();

            SingleDataResponse<String> response = responseService.getSingleDataResponse(true, userId, token.getAccessToken());
            responseEntity = ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(response);

        } catch (Exception exception) {
            //log.debug(exception.getMessage());
            System.out.println(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @RequestMapping("/loginFail")
    public String loginFail(){
        System.out.println("로그인실패");
        return "login fail";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        System.out.println("로그아웃");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
