package com.yhao.controller;

import com.yhao.domain.entity.Comment;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 15:28
 **/
@RestController
@Api(description="点评管理")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private I18nUtil i18nUtil;

    @ApiOperation(value="获取用户列表", notes="获取列表，以各种姿势（顺序）")
    @PostMapping("/list")
    public BaseResult<List<Comment>> getUserList(@ApiParam("排列顺序：STAR_DESC,面膜分值降序;STAR_ASC,面膜分值升序;TIME_DESC,发起时间升序;TIME_ASC,发起时间降序;COUNT_DESC,点评数量降序;COUNT_ASC,点评数量升序;")
                                         @RequestParam String desc) {
        return new BaseResult(commentService.findByDesc(desc),ResultStatus.SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

    @ApiOperation(value="添加点评", notes="发起点评一次+5")
    @PostMapping("/add")
    public BaseResult<Comment> add(@NotNull(message = "userId不能为空") @RequestParam Integer userId,
                          @ApiParam("图片") @RequestParam String pictrue,
                          @ApiParam("内容") @RequestParam String data){
        return BaseResult.success(commentService.addComment(userId, pictrue, data));
    }

}
