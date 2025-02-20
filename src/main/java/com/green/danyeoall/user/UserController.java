package com.green.danyeoall.user;

import com.green.danyeoall.common.model.ResultResponse;
import com.green.danyeoall.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "1. 회원", description = "signup / signout")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary = "회원 가입")
    public ResultResponse<Integer> postSignUp(@RequestBody UserSignUpReq p) {
        log.debug(p.getEmail() + " / " +p.getNickName()+"님의 회원가입을 진행합니다.");
        int result = service.postSignUp(p);

        String resultMsg = null;
        if(result > 0)
        {
            resultMsg = "회원가입이 정상적으로 되었습니다.";
        } else {
            resultMsg = "회원가입이 실패했습니다.";
        }

        return ResultResponse.<Integer>builder()
                .resultMessage(resultMsg)
                .resultData(result)
                .build();
    }

    @PostMapping("sign-in")
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

    @GetMapping("schedule")
    @Operation(summary = "마이페이지 유저 플랜 리스트 보여주기")
    public ResultResponse<List<UserSelPlanListRes>> getUserPlanList(@ParameterObject UserSelPlanListReq p) {
        List<UserSelPlanListRes> res = service.getUserPlanList(p);
        return ResultResponse.<List<UserSelPlanListRes>>builder()
                .resultMessage("플랜 리스트 가져오기 성공")
                .resultData(res)
                .build();
    }

    @PatchMapping("nickname")
    @Operation(summary = "닉네임 수정")
    public ResultResponse<Integer> patchUserNickName(@RequestBody UserUpdNickNameReq p) {
        int result = service.patchUserNickName(p);
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

    @PatchMapping("password")
    @Operation(summary = "비밀번호 수정")
    public ResultResponse<Integer> patchUserPassword(@RequestBody UserUpdPasswordReq p) {
        int result = service.patchUserPassword(p);
        String resultMsg = null;
        if(result > 0){
            resultMsg = "정상적으로 수정 되었습니다.";
        } else {
            resultMsg = "실패하였습니다.";
        }
        return ResultResponse.<Integer>builder()
                .resultMessage(resultMsg)
                .resultData(result)
                .build();
    }

    @PatchMapping("upw")
    @Operation(summary = "이메일 인증 후 비밀번호를 수정")
    public ResultResponse<Integer> putUserUpw(@RequestBody UserPatchNewUpwReq p) {
        int result = service.patchUserUpw(p);

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

    @DeleteMapping
    @Operation(summary = "비밀번호가 맞다면 회원 탈퇴 처리")
    public ResultResponse<Integer> delUser(@ParameterObject @ModelAttribute UserDelReq p) {
        int result = service.delUser(p);
        String resultMsg = null;
        if(result == 1) {
            resultMsg = "탈퇴가 완료되었습니다.";
        } else if (result == 2) {
            resultMsg = "비밀번호가 틀렸습니다.";
        } else {
            resultMsg = "탈퇴가 실패하였습니다.";
        }
        return ResultResponse.<Integer>builder()
                .resultMessage(resultMsg)
                .resultData(result)
                .build();
    }
}
