package com.yhao.service;

import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.User;
import com.yhao.enums.ResultStatus;
import com.yhao.result.BaseResult;
import com.yhao.util.I18nUtil;
import com.yhao.util.Random;
import com.yhao.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨浩
 * @create 2018-09-19 12:21
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private I18nUtil i18nUtil;

    public BaseResult sendMessage(String phone){
        User user = userMapper.findByPhone(phone);
        String checkCode = Random.numeric(6);
        if(user == null){
            IndustrySMS.execute(phone, checkCode);
            userMapper.insert(phone, "girl_" + phone.substring(7, 11), 2, checkCode);
        }else {
            user.setCheckCode(checkCode);
            IndustrySMS.execute(phone, checkCode);
            userMapper.update(user);
        }
        return new BaseResult(ResultStatus.SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

    public BaseResult edit(Integer userId,String icon, String name, Integer sex) {
        User user = userMapper.findById(userId);
        if (user == null) {
            return new BaseResult(ResultStatus.NOT_EXIST.getCode(),i18nUtil.getMessage(ResultStatus.NOT_EXIST.getMessage()));
        }
        if(StringUtil.isBlank(icon) && StringUtil.isBlank(name) && sex == null){
            return BaseResult.success();
        }
        if(!StringUtil.isBlank(name)){
            user.setName(name);
        }
        if(!StringUtil.isBlank(icon)){
            user.setIcon(icon);
        }
        if (sex != null) {
            user.setSex(sex);
        }
        userMapper.update(user);
        return new BaseResult(ResultStatus.SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
    }

    public BaseResult login(String phone, String checkCode) {
        User user = userMapper.findByPhone(phone);
        if(user == null){
            return BaseResult.failure(ResultStatus.USER_NOT_EXIST);
        } else if (!checkCode.equals(user.getCheckCode())) {
            return BaseResult.failure(ResultStatus.CHECK_CODE_ERROP);
        }
        return new BaseResult(user,ResultStatus.LOGIN_SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.LOGIN_SUCCESS.getMessage()));
    }

    public void reducePoint(Integer userId, Integer score) {
        User user = userMapper.findById(userId);
        int point = user.getPoint();
        if (point > 0 && score > 0) {
            user.setPoint(point - score);
            userMapper.update(user);
        }
    }

    public void addPoint(Integer userId, Integer score) {
        User user = userMapper.findById(userId);
        int point = user.getPoint();
        if (score > 0) {
            user.setPoint(point + score);
            userMapper.update(user);
        }
    }
}
