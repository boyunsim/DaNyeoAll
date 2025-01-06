package com.green.danyeoall.plan.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelPlanDetailReq {
    @Schema(title = "플랜 PK")
    private long planId;
}
