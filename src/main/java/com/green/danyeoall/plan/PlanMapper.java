package com.green.danyeoall.plan;


import com.green.danyeoall.plan.detail.DelPlanDetailReq;
import com.green.danyeoall.plan.detail.InsPlanDetailReq;
import com.green.danyeoall.plan.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    int insPlan(InsPlanReq p);
    InsPlanRes selMasterPlan (long planMasterId);

//    int insPlanDetail(InsPlanDetailReq p);
    int insPlanDetail(InsPlanDetailReq p);

    List<SelPlanDto> selPlanList(SelPlanReq p);
    SelPlanRes selPlanOnly(SelPlanReq p);

    SelExistPlaceRes selExistCityPlaceByPlaceName(String placeName);

    SelPlanPriceRes selPlanSumPrice(SelPlanReq p);
    List<SelPlanPriceRes> selPlanDayPrice(SelPlanReq p);

    int updPlan(UpdPlanReq p);

    int delPlan(DelPlanReq p);
    int delPlanDetail(DelPlanDetailReq p);
}
