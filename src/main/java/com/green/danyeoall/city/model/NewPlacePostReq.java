package com.green.danyeoall.city.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPlacePostReq {
    @JsonIgnore
    private long placeId;
    @Schema(title = "도시 PK", example = "1")
    private long cityId;
    @Schema(title = "장소 주소", example = "대구 광역시 ~~")
    private String placeAddress;
    @Schema(title = "장소 이름", example = "그린컴퓨터아트학원")
    private String placeName;
    @Schema(title = "카테고리", example = "place")
    private String category;
    @Schema(title = "위도")
    private double lat;
    @Schema(title = "경도")
    private double lng;
}
