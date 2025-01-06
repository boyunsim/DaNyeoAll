package com.green.danyeoall.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSelPlanListReq {
    @Schema(title = "접속한 유저 PK", example = "8")
    private long userId;
}
