package com.green.danyeoall.plan.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsPlanRes {
    private long planMasterId;
    private int planDate;
}
