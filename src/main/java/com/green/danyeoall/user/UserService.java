package com.green.danyeoall.user;

import com.green.danyeoall.feed.model.FeedGetRes;
import com.green.danyeoall.mail.MailMapper;
import com.green.danyeoall.mail.model.MailCheckDto;
import com.green.danyeoall.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final MailMapper mailMapper;

    public int postSignUp(UserSignUpReq p) {
        int result = 0;
        int auth = mailMapper.selAuthCheck(p.getEmail());
        MailCheckDto dto = mailMapper.selAuthInfo(p.getEmail());
        if( auth == 0 ) {
            return result;
        }else if(!LocalDateTime.now().isBefore(dto.getExpiredTime())) {
            return result;
        }

        try {
            String hashedPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
            UserService.log.debug("hashedPassword : {}", hashedPassword);
            p.setUpw(hashedPassword);

            result = userMapper.insUser(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mailMapper.delAuthInfo(p.getEmail());

        return result;
    }

    public UserSignInRes postSignIn(UserSignInReq p) {
        UserSignInRes res = userMapper.postSignIn(p.getEmail());
        return res;
    }

    public List<UserSelPlanListRes> getUserPlanList(UserSelPlanListReq p) {
        List<UserSelPlanListRes> res = userMapper.selUserPlanList(p.getUserId());
        return res;
    }

    public int patchUserNickName(UserUpdNickNameReq p) {
        int result = userMapper.updUserNickName(p);
        return result;
    }

    public int patchUserPassword(UserUpdPasswordReq p) {
        UserSignInRes res = userMapper.getUpwByUserId(p.getUserId());
        log.info("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh{}", p);
        log.info("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh{}", res);
        if( !BCrypt.checkpw(p.getUpw(), res.getUpw()) ) {
            return 0;
        }
        String hashedPassword = BCrypt.hashpw(p.getNewUpw(), BCrypt.gensalt());
        p.setNewUpw(hashedPassword);
        log.info("바뀐p >>>>> {} " , p);
        int result = userMapper.updUserPassword(p);

        return result;
    }

    public int patchUserUpw(UserPatchNewUpwReq p) {
        String hashedPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        UserService.log.debug("hashedPassword : {}", hashedPassword);
        p.setUpw(hashedPassword);
        int result = userMapper.patchUserUpw(p);
        mailMapper.delAuthInfo(p.getEmail());
        return result;
    }

    public int delUser(UserDelReq p) {
        String password = p.getUpw();
        String hashedPassword = userMapper.selHashedUpwByUserId(p.getUserId());
        if(BCrypt.checkpw(password, hashedPassword)) {
            int result = userMapper.deleteUser(p);
            return result;
        }
        return 2;
    }

}
