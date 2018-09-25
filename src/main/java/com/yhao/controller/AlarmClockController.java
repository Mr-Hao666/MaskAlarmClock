package com.yhao.controller;

import com.yhao.domain.AlarmClockMapper;
import com.yhao.domain.entity.AlarmClock;
import com.yhao.enums.ResultStatus;
import com.yhao.result.BaseResult;
import com.yhao.util.I18nUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 15:00
 **/
@RestController
@Api(description="闹钟管理")
@RequestMapping("/alarmClock")
public class AlarmClockController {

    @Autowired
    private AlarmClockMapper alarmClockMapper;

    @Autowired
    private I18nUtil i18nUtil;

    @ApiOperation(value="获取主题闹钟列表", notes="获取主题闹钟列表")
    @PostMapping("/list")
    public BaseResult<List<AlarmClock>> list(){
        return new BaseResult(alarmClockMapper.findAll(),ResultStatus.SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

}
