package com.green.danyeoall.user;

import com.green.danyeoall.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insUser(UserSignUpReq p);
    UserSignInRes postSignIn(String uid);
    UserSignInRes getUpwByUserId(long userId);
    int updUserNickName(UserUpdNickNameReq p);
    int updUserPassword(UserUpdPasswordReq p);
    int patchUserUpw(UserPatchNewUpwReq p);
    int deleteUser(UserDelReq p);
    String selHashedUpwByUserId(long userId);
    List<UserSelPlanListRes> selUserPlanList(long userId);
}
