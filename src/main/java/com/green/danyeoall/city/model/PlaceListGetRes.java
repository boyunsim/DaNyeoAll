package com.green.danyeoall.city.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceListGetRes {
    @Schema(title = "PK")
    private long placeId;

    @Schema(title = "장소 이름", example = "서울남산타워")
    private String placeName;

    @Schema(title = "장소 사진")
    private String placePic;

    @Schema(title = "장소 주소", example = "서울특별시 ~~")
    private String placeAddress;

    @Schema(title = "별점", example = "4.5")
    private double placeStar;

    @Schema(title = "위도", example = "32.5")
    private double lat;

    @Schema(title = "경도", example = "132.5")
    private double lng;

}
