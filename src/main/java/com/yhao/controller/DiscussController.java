package com.yhao.controller;

import com.yhao.domain.DiscussMapper;
import com.yhao.domain.entity.Comment;
import com.yhao.domain.entity.Discuss;
import com.yhao.enums.ResultStatus;
import com.yhao.result.BaseResult;
import com.yhao.service.CommentService;
import com.yhao.util.I18nUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 17:42
 **/
@RestController
@Api(description = "评论管理")
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    private DiscussMapper discussMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private I18nUtil i18nUtil;

    @ApiOperation(value = "获取用户点评", notes = "获取点评列表，以评论时间倒叙")
    @PostMapping("/list")
    public BaseResult<List<Discuss>> getList(@ApiParam("点评ID")
                                 @RequestParam Integer commentId) {
        return new BaseResult(discussMapper.findByCommentId(commentId),ResultStatus.SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

    @ApiOperation(value = "添加点评", notes = "添加点评")
    @PostMapping("/add")
    public BaseResult add(@ApiParam("点评用户ID") @RequestParam Integer userId,
                          @ApiParam("点评ID") @RequestParam Integer commentId,
                          @ApiParam("星值") @RequestParam Integer star,
                          @ApiParam("点评内容") @RequestParam String remark) {
        return new BaseResult(commentService.addDiscuss(userId, commentId, star, remark),ResultStatus.SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }


}
