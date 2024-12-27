package com.green.danyeoall.user;

import com.green.danyeoall.mail.MailMapper;
import com.green.danyeoall.user.model.UserSignInReq;
import com.green.danyeoall.user.model.UserSignInRes;
import com.green.danyeoall.user.model.UserSignUpReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final MailMapper mailMapper;

    public int postSignUp(UserSignUpReq p) {
        int result = 0;
        try {
            String hashedPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
            UserService.log.debug("hashedPassword : {}", hashedPassword);
            p.setUpw(hashedPassword);

            result = userMapper.insUser(p);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public UserSignInRes postSignIn(UserSignInReq p) {
        UserSignInRes res = null;
        try {
            res = userMapper.postSignIn(p.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int patchUser(UserSignUpReq p) {
        int result = 0;
        try {
            String hashedPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
            log.debug("hashedPassword: {}", hashedPassword);
            p.setUpw(hashedPassword);

            result = userMapper.patchUser(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
