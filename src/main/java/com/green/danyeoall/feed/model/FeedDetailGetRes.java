package com.green.danyeoall.feed.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FeedDetailGetRes {
    @Schema(title = "도시 PK")
    private long cityId;
    @Schema(title = "피드 제목")
    private String title;
    @Schema(title = "피드 내용")
    private String content;
    @Schema(title = "도시 이름")
    private String cityName;
    @Schema(title = "도시 색상")
    private String color;
    @Schema(title = "플랜 시작일")
    private String startDate;
    @Schema(title = "플랜 종료일")
    private String endDate;
    @Schema(title = "인원")
    private int peopleCnt;
    @Schema(title = "좋아요", description = "2: 로그인 유저 아님 1: 좋아요, 0: 좋아요 아님")
    private int isLike;
    @Schema(title = "유저 PK")
    private long userId;
    @Schema(title = "여행 일정")
    private List<FeedDetailGetDto> feedDetailList;
}
