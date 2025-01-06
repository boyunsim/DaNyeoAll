package com.green.danyeoall.plan;


import com.green.danyeoall.common.model.ResultResponse;
import com.green.danyeoall.plan.detail.DelPlanDetailReq;
import com.green.danyeoall.plan.detail.InsPlanDetailReq;
import com.green.danyeoall.plan.detail.InsPlanDetailRes;
import com.green.danyeoall.plan.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("plan")
@Tag(name = "Plan", description = "Plan 관리")
public class PlanController {
    private final PlanService service;

    @PostMapping
    @Operation(summary = "plan 등록")
    public ResultResponse<InsPlanRes> postPlan(@RequestBody InsPlanReq p){
        InsPlanRes result = service.postPlan(p);
        return ResultResponse.<InsPlanRes>builder()
                .resultMessage("Plan 등록")
                .resultData(result)
                .build();
    }

    @PostMapping("detail")
    @Operation(summary = "planDetail 등록")
    public ResultResponse<InsPlanDetailRes> postNewPlanDetail(@RequestBody InsPlanDetailReq p){
        InsPlanDetailRes result = service.postPlanDetail(p);
        return ResultResponse.<InsPlanDetailRes>builder()
                .resultMessage("신규 PlanDetail 등록")
                .resultData(result)
                .build();
    }

    @GetMapping
    @Operation(summary = "PlanList 보여주기")
    public ResultResponse<SelPlanRes> getPlanList(@ParameterObject @ModelAttribute SelPlanReq p){
        SelPlanRes res = service.getPlanList(p);
        return ResultResponse.<SelPlanRes>builder()
                .resultMessage("Plan 리스트 보여주기")
                .resultData(res)
                .build();
    }

    @GetMapping("sum")
    @Operation(summary = "Plan 총 가격 보여주기")
    public ResultResponse<SelPlanPriceRes> getPlanSumPrice(@ParameterObject @ModelAttribute SelPlanReq p){
        SelPlanPriceRes res = service.getPlanSumPrice(p);

        return ResultResponse.<SelPlanPriceRes>builder()
                .resultMessage("Plan 총 가격 보여주기")
                .resultData(res)
                .build();

    }

    @GetMapping("day")
    @Operation(summary = "Plan 날짜별 가격 보여주기")
    public ResultResponse<List<SelPlanPriceRes>> getPlanDayList(@ParameterObject @ModelAttribute SelPlanReq p){
        List<SelPlanPriceRes> res = service.getPlanDayPrice(p);
        return ResultResponse.<List<SelPlanPriceRes>>builder()
                .resultMessage("Plan 날짜별 가격 보여주기")
                .resultData(res)
                .build();
    }

    @PutMapping
    public ResultResponse<Integer> putPlan(@RequestBody UpdPlanReq p){
        int result = service.putPlan(p);

        return ResultResponse.<Integer>builder()
                .resultMessage("Plan 수정")
                .resultData(result)
                .build();
    }

    @DeleteMapping
    public ResultResponse<Integer> deletePlan(@ModelAttribute DelPlanReq p){
        int result = service.deletePlan(p);

        return ResultResponse.<Integer>builder()
                .resultMessage("Plan 삭제")
                .resultData(result)
                .build();
    }

    @DeleteMapping("detail")
    public ResultResponse<Integer> deletePlanDetail(@ModelAttribute DelPlanDetailReq p){
        int result = service.deleteDetailPlan(p);

        return ResultResponse.<Integer>builder()
                .resultMessage("Plan Detail 삭제")
                .resultData(result)
                .build();
    }
}
