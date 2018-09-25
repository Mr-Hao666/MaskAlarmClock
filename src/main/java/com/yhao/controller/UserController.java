package com.yhao.controller;

import com.yhao.domain.UserCollectionMapper;
import com.yhao.domain.entity.AlarmClock;
import com.yhao.domain.entity.User;

import java.util.*;

import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.UserCollection;
import com.yhao.enums.ResultStatus;
import com.yhao.result.BaseResult;
import com.yhao.service.IndustrySMS;
import com.yhao.service.UserCollectionService;
import com.yhao.service.UserService;
import com.yhao.util.I18nUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api(description = "用户管理")
@RequestMapping("/user")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

    @Autowired
    private UserMapper users;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private I18nUtil i18nUtil;

    @ApiOperation(value = "获取用户列表", notes = "")
    @PostMapping("/list")
    public BaseResult<List<User>> getUserList() {
        return new BaseResult(users.findAll(), ResultStatus.SUCCESS.getCode(), i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

    /*@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.insert(user.getId(), user);
        return "success";
    }*/

    @ApiOperation(value = "获取用户详细信息", notes = "根据id来获取用户详细信息")
    @PostMapping("/getUser")
    public BaseResult<User> getUser(@NotNull(message = "userId不能为空") @ApiParam("用户ID") @RequestParam Integer userId,
                                    @ApiParam("语言设置") @RequestParam(required = false) String lang) {
        return new BaseResult(users.findById(userId), ResultStatus.SUCCESS.getCode(), i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

    @ApiOperation(value = "用户登录", notes = "手机号登录")
    @PostMapping("/login")
    public BaseResult login(@NotNull(message = "phone不能为空") @ApiParam("手机号码") @RequestParam String phone,
                            @NotNull(message = "验证码不能为空") @ApiParam("验证码") @RequestParam String checkCode) {
        return userService.login(phone, checkCode);
    }

    @ApiOperation(value = "用户获取验证码", notes = "获取验证码")
    @PostMapping("/sendCheckCode")
    public BaseResult sendSMS(@NotNull(message = "phone不能为空") @RequestParam String phone,
                              @ApiParam("语言设置") @RequestParam(required = false) String lang) {
        return userService.sendMessage(phone);
    }

    @ApiOperation(value = "用户添加收藏", notes = "用户添加收藏")
    @PostMapping("/collection/add")
    public BaseResult addCollection(@NotNull(message = "userId不能为空") @RequestParam Integer userId,
                                    @NotNull(message = "alarmClockId不能为空") @RequestParam Integer alarmClockId) {
        return userCollectionService.add(userId, alarmClockId);
    }

    @ApiOperation(value = "用户收藏列表", notes = "用户收藏列表")
    @PostMapping("/collection/List")
    public BaseResult<List<AlarmClock>> getCollection(@NotNull(message = "userId不能为空") @RequestParam Integer userId) {
        return new BaseResult(userCollectionService.list(userId), ResultStatus.SUCCESS.getCode(), i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

    @ApiOperation(value = "用户修改信息", notes = "用户修改信息")
    @PostMapping("/edit")
    public BaseResult edit(@NotNull(message = "userId不能为空") @RequestParam Integer userId,
                           @RequestParam(required = false) String icon,
                           @RequestParam(required = false) Integer sex,
                           @RequestParam(required = false) String name) {
        return userService.edit(userId, icon, name, sex);
    }

}