package com.green.danyeoall.plan;


import com.green.danyeoall.city.CityMapper;
import com.green.danyeoall.city.model.NewPlacePostReq;
import com.green.danyeoall.plan.detail.DelPlanDetailReq;
import com.green.danyeoall.plan.detail.InsPlanDetailReq;
import com.green.danyeoall.plan.detail.InsPlanDetailRes;
import com.green.danyeoall.plan.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {
    private final PlanMapper mapper;
    private final CityMapper cityMapper;

    public InsPlanRes postPlan(InsPlanReq p){
        int result = mapper.insPlan(p);
        InsPlanRes res = mapper.selMasterPlan(p.getPlanMasterId());
        return res;
    }

    public InsPlanDetailRes postPlanDetail(InsPlanDetailReq p){
        NewPlacePostReq req = p.getNewPlacePostReq();
        SelExistPlaceRes exist = mapper.selExistCityPlaceByPlaceName(req.getPlaceName());
        if(exist.getExist() != 1) {
            int result2 = cityMapper.insSight(req);
            p.setPlaceId(req.getPlaceId());
            int result = mapper.insPlanDetail(p);
            InsPlanDetailRes res = new InsPlanDetailRes();
            res.setDate(p.getDate());
            return res;
        }
        p.setPlaceId(exist.getPlaceId());
        int result = mapper.insPlanDetail(p);
        InsPlanDetailRes res = new InsPlanDetailRes();
        res.setDate(p.getDate());
        res.setPlanId(p.getPlanId());
        return res;
    }

    public SelPlanRes getPlanList(SelPlanReq p){
        SelPlanRes res = mapper.selPlanOnly(p);
        List<SelPlanDto> selPlanDtoList = mapper.selPlanList(p);
        for(SelPlanDto item : selPlanDtoList) {
            String startTime = item.getStartTime();
            item.setStartTime(startTime.substring(0, startTime.lastIndexOf(":")));
            String endTime = item.getEndTime();
            item.setEndTime(endTime.substring(0, endTime.lastIndexOf(":")));
        }
        res.setSelPlanDtoList(selPlanDtoList);

        return res;
    }

    public SelPlanPriceRes getPlanSumPrice(SelPlanReq p){
        SelPlanPriceRes res = mapper.selPlanSumPrice(p);
        return res;
    }

    public List<SelPlanPriceRes> getPlanDayPrice(SelPlanReq p){
        List<SelPlanPriceRes> res = mapper.selPlanDayPrice(p);
        return res;
    }

    public int putPlan(UpdPlanReq p){
        int result = mapper.updPlan(p);

        return result;
    }

    public int deletePlan(DelPlanReq p){
        int result = mapper.delPlan(p);
        return result;
    }

    public int deleteDetailPlan(DelPlanDetailReq p){
        int result = mapper.delPlanDetail(p);
        return result;
    }
}
