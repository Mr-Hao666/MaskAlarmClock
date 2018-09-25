package com.yhao.controller;

import com.yhao.domain.entity.Discuss;
import com.yhao.domain.entity.FaceScore;
import com.yhao.result.BaseResult;
import com.yhao.service.FaceScoreService;
import com.yhao.util.I18nUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨浩
 * @create 2018-09-19 18:02
 **/
@RestController
@Api(description="颜值管理")
@RequestMapping("/faceScore")
public class FaceScoreController {

    @Autowired
    private FaceScoreService faceScoreService;

    @ApiOperation(value = "美容护理", notes = "美容护理")
    @PostMapping("/add")
    public BaseResult add(@ApiParam("点评用户ID") @RequestParam Integer userId,
                          @ApiParam("变动类型：1补水，2美白，3紧致，4滋润，5听音乐") @RequestParam Integer type) {
        return faceScoreService.add(userId, type);
    }

}
