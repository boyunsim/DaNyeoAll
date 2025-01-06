package com.green.danyeoall.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.danyeoall.common.model.Paging;
import lombok.Getter;

@Getter
public class FeedGetReq {
    @JsonIgnore
    private Long planMasterId;
}
