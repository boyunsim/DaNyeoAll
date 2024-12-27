package com.green.danyeoall.user;

import com.green.danyeoall.common.model.ResultResponse;
import com.green.danyeoall.user.model.UserSignInReq;
import com.green.danyeoall.user.model.UserSignInRes;
import com.green.danyeoall.user.model.UserSignUpReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "1. 회원", description = "signup / signout")
public class UserController {
    private final UserService service;

    @PostMapping("/api/user/signup")
    @Operation(summary = "회원 가입")
    public ResultResponse<Integer> postSignUp(@RequestBody UserSignUpReq p) {
        log.debug(p.getEmail() + " / " +p.getNickName()+"님의 회원가입을 진행합니다.");
        int result = service.postSignUp(p);

        String resultMsg = null;
        if(result > 0)
        {
            resultMsg = "회원가입이 정상적으로 되었습니다.";
        } else {
            resultMsg = "회원가입이 실패하였습니다.";
        }

        return ResultResponse.<Integer>builder()
                .resultMessage(resultMsg)
                .resultData(result)
                .build();
    }

    @PostMapping("/api/user/signin")
    @Operation(summary = "로그인")
    public ResultResponse<UserSignInRes> postSignIn(@RequestBody UserSignInReq p) {
        UserSignInRes res = service.postSignIn(p);

        if( res == null || !BCrypt.checkpw(p.getUpw(), res.getUpw()) )
        {
            res = new UserSignInRes();
            res.setMessage("이메일/비밀번호를 확인해 주세요.");
        } else {
            res.setMessage("로그인 성공");
        }

        return ResultResponse.<UserSignInRes>builder()
                .resultMessage("회원정보 조회 수행을 완료하였습니다.")
                .resultData(res)
                .build();
    }

    @PatchMapping("/api/user")
    @Operation(summary = "닉네임과 비밀번호를 수정")
    public ResultResponse<Integer> putUser(@RequestBody UserSignUpReq p) {
        log.debug("UserController > putUser > p: {}", p.toString());
        int result = service.patchUser(p);

        String resultMsg = null;
        if(result > 0)
        {
            resultMsg = "정상적으로 수정 되었습니다.";
        } else {
            resultMsg = "실패하였습니다.";
        }

        return ResultResponse.<Integer>builder()
                .resultMessage(resultMsg)
                .resultData(result)
                .build();
    }
}
