package com.green.danyeoall.plan.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SelPlanDto {
    @Schema(title = "플랜 PK")
    private long planId;
    @Schema(title = "장소 PK")
    private long placeId;
    @Schema(title = "가격")
    private int price;
    @Schema(title = "메모")
    private String memo;
    @Schema(title = "출발 시간")
    private String startTime;
    @Schema(title = "도착 시간")
    private String endTime;
    @Schema(title = "몇 일차")
    private int date;
    @Schema(title = "카테고리")
    private String category;
    @Schema(title = "장소 이름")
    private String placeName;
    @Schema(title = "장소 주소")
    private String placeAddress;
}
