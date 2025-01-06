package com.green.danyeoall.plan.model;


import com.green.danyeoall.feed.model.FeedDetailGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelPlanRes {
    @Schema(title = "일정 Master PK")
    private long planMasterId;
    @Schema(title = "출발 날짜")
    private String startDate;
    @Schema(title = "도착 날짜")
    private String endDate;
    @Schema(title = "사람 숫자")
    private int peopleCnt;
    @Schema(title = "총 여행 일수")
    private int planDate;
    @Schema(title = "도시 이름")
    private String cityName;
    @Schema(title = "도시 색깔")
    private String color;
    @Schema(title = "도시 PK")
    private long cityId;
    @Schema(title = "여행 일정")
    private List<SelPlanDto> selPlanDtoList;
}
