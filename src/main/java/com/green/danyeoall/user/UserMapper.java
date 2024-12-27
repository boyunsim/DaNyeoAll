package com.green.danyeoall.user;

import com.green.danyeoall.user.model.UserSignInRes;
import com.green.danyeoall.user.model.UserSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignUpReq p);
    UserSignInRes postSignIn(String uid);
    int patchUser(UserSignUpReq p);
}
