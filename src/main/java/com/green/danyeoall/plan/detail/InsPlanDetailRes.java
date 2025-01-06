package com.green.danyeoall.plan.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsPlanDetailRes {
    @Schema(title = "몇 일차", example = "1")
    private String date;
    @Schema(title = "플랜디테일 PK", example = "38")
    private long planId;
}
