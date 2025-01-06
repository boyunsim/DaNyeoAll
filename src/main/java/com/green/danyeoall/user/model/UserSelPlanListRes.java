package com.green.danyeoall.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSelPlanListRes {
    private long planMasterId;
    private long cityId;
    private String cityKorName;
    private String cityPic;
    private String startDate;
    private String planDate;
    private String color;
}
