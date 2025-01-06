package com.green.danyeoall.feed.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDetailGetDto {
    @Schema(title = "명소 PK")
    private long placeId;
    @Schema(title = "비용")
    private int price;
    @Schema(title = "메모")
    private String memo;
    @Schema(title = "플랜 시작 시간")
    private String startTime;
    @Schema(title = "플랜 종료 시간")
    private String endTime;
    @Schema(title = "일차")
    private int date;

}
