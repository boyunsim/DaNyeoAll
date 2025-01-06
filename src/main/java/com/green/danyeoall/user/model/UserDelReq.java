package com.green.danyeoall.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDelReq {
    @Schema(description = "로그인한 유저 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(description = "유저 입력 비밀번호", example = "testtest1212!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
}
