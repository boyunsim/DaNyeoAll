package com.green.danyeoall.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPatchNewUpwReq {
    @Schema(description = "비번을 변경할 유저 email", example = "by5028@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(description = "변경할 비밀번호", example = "testtest1212!!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
}
