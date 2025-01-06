package com.green.danyeoall.plan.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelPlanReq {
    @Schema(title = "PlanMaster PK", example = "8")
    private long planMasterId;
}
