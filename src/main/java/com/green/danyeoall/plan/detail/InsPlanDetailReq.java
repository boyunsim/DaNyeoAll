package com.green.danyeoall.plan.detail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.danyeoall.city.model.NewPlacePostReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsPlanDetailReq {
    @Schema(title = "플랜 PK", example = "47")
    private Long planMasterId;

    @JsonIgnore
    private long planId;

    @Schema(hidden = true)
    private long placeId;

    @Schema(title = "가격", example = "50000")
    private int price;
    @Schema(title = "장소소개", example = "장소소개")
    private String memo;
    @Schema(title = "출발 시간", example = "16:00:00")
    private String startTime;
    @Schema(title = "종료 시간", example = "18:00:00")
    private String endTime;
    @Schema(title = "몇 일차", example = "1")
    private String date;

    @Schema(title = "새로운 장소 정보")
    private NewPlacePostReq newPlacePostReq;
}
