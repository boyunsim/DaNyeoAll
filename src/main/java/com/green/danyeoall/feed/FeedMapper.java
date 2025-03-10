package com.green.danyeoall.feed;

import com.green.danyeoall.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    List<FeedGetRes> selFeedListMain(FeedGetReq p);
    List<FeedGetRes> selFeedList(FeedGetReq p);
    List<FeedDetailGetDto> selFeedDetail(FeedDetailGetReq p);
    FeedDetailGetRes selFeedOnlyPlan(FeedDetailGetReq p);
    List<FeedGetRes> selFeedListLatest(FeedGetReq p);
    int insFeed(FeedPostReq p);
    int updFeed(FeedPutReq p);
    int delFeed(FeedDelReq p);
}
