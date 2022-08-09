package com.example.springtest.security.mapper;

import com.example.springtest.security.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface LoginMapper {
    //CustomUserDetails readUsername(String id);
    void join(UserModel userVo);
    Optional<UserModel> findUser(String userId);
    Optional<UserModel> findUserId(String userId);
}
