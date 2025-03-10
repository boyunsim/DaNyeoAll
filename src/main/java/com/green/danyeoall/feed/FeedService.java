package com.green.danyeoall.feed;

import com.green.danyeoall.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;

    public int insFeed(FeedPostReq p) {
        int result = mapper.insFeed(p);
        return result;
    }

    public List<FeedGetRes> selFeedListMain(FeedGetReq p) {
        List<FeedGetRes> list = mapper.selFeedListMain(p);
        for(FeedGetRes item : list){
            String createdAt = item.getCreatedAt();
            item.setCreatedAt(createdAt.substring(0, createdAt.lastIndexOf("-") + 3));
        }
        return list;
    }

    public List<FeedGetRes> selFeedList(FeedGetReq p) {
        log.info("selFeedListMain : {}", p);
        List<FeedGetRes> list = mapper.selFeedList(p);
        for(FeedGetRes item : list){
            String createdAt = item.getCreatedAt();
            item.setCreatedAt(createdAt.substring(0, createdAt.lastIndexOf(":")));
        }
        return list;
    }

    public List<FeedGetRes> selFeedListLatest(FeedGetReq p) {
        log.info("selFeedListMain : {}", p);
        List<FeedGetRes> list = mapper.selFeedListLatest(p);
        for(FeedGetRes item : list){
            String createdAt = item.getCreatedAt();
            item.setCreatedAt(createdAt.substring(0, createdAt.lastIndexOf(":")));
        }
        return list;
    }

    public FeedDetailGetRes selFeedDetail(FeedDetailGetReq p) {

        FeedDetailGetRes res = mapper.selFeedOnlyPlan(p);

        List<FeedDetailGetDto> feedDetailGetDtoList = mapper.selFeedDetail(p);

        res.setFeedDetailList(feedDetailGetDtoList);

        return res;
    }

    public int updFeed(FeedPutReq p) {
        int result = mapper.updFeed(p);
        return result;
    }

    public int delFeed(FeedDelReq p) {
        int result = mapper.delFeed(p);
        return result;
    }
}
