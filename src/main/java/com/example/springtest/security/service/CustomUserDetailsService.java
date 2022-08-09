package com.example.springtest.security.service;

import com.example.springtest.security.mapper.LoginMapper;
import com.example.springtest.security.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    LoginMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
            return userMapper.findUser(userId)
                    .map(user -> addAuthorities(user))
                    .orElseThrow(() -> new UsernameNotFoundException(userId + ">ID를 찾을 수 없습니다."));
    }

    private UserModel addAuthorities(UserModel userModel) {
        userModel.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userModel.getUserRole())));

        return userModel;
    }
}
