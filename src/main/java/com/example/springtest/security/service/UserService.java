package com.example.springtest.security.service;

import com.example.springtest.security.jwt.TokenProvider;
import com.example.springtest.security.mapper.LoginMapper;
import com.example.springtest.security.model.LoginModel;
import com.example.springtest.security.model.TokenModel;
import com.example.springtest.security.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    // 암호화 위한 엔코더
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final TokenProvider jwtTokenProvider;

    // 회원가입 시 저장시간을 넣어줄 DateTime형
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    @Autowired
    LoginMapper userMapper;


    /**
     * 유저 회원가입
     * @param
     */
    @Transactional
    public boolean join(UserModel user) throws Exception {
        // 가입된 유저인지 확인
        if (userMapper.findUserId(user.getUserId()).isPresent()) {
            System.out.println("!!!");
            throw new Exception("이미 가입된 유저에요");
        }


        // 가입 안했으면 아래 진행
        UserModel userVo = UserModel.builder()
        .userId(user.getUserId())
        .userPw(passwordEncoder.encode(user.getUserPw()))
        .userRole("ROLE_USER")
        .createDate(localTime)
        .updateDate(localTime)
        .build();

        userMapper.join(userVo);
        // userMapper.addRole(userVo);

        return userMapper.findUserId(user.getUserId()).isPresent();
    }
    /**
     * 토큰 발급받는 메소드
     * @param loginDTO 로그인 하는 유저의 정보
     * @return result[0]: accessToken, result[1]: refreshToken
     */
    public String login (LoginModel loginDTO) throws Exception {

        UserModel userDto = userMapper.findUser(loginDTO.getUserId())//indUserByUsername(loginDto.getUsername())
                .orElseThrow(() -> new Exception("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDTO.getUserPw(), passwordEncoder.encode(userDto.getPassword()))) {
            throw new Exception("잘못된 비밀번호입니다");
        }

        return userDto.getUserId();
        // return loginDTO.getUserId();
        // // return tokenGenerator(userDto);
    }

    /**
     * 유저가 db에 있는지 확인하는 함수
     * @param userid 유저의 아이디 입력
     * @return 유저가 있다면: true, 유저가 없다면: false
     */
    public boolean haveUser(String userid) {
        // IdDTO idDTO = IdDTO.builder().userId(userid).build();
        if (userMapper.findUserId(userid).isPresent()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 유저의 아이디를 찾는 함수
     * @param userId 유저의 아이디 입력
     * @return 유저의 아이디가 없다면 에러를 뱉고, 있다면 userId리턴
     */
    public UserModel findUserId(String userId) throws Exception {
        return userMapper.findUserId(userId)
                .orElseThrow(() ->
                    new Exception("유저 중볶!~!!!!."));
    }

    public TokenModel tokenGenerator(String userId) throws Exception {

        UserModel userDto = userMapper.findUser(userId)//indUserByUsername(loginDto.getUsername())
        .orElseThrow(() -> new Exception("잘못된 아이디입니다"));

        return TokenModel.builder()
        .accessToken("Bearer" + jwtTokenProvider.createAcessToken(userDto.getUserId(), Collections.singletonList(userDto.getUserRole())))
        .refreshToken("Bearer" + jwtTokenProvider.createRefreshToken(userDto.getUserId(), Collections.singletonList(userDto.getUserRole())))
        .build();
    }
}
