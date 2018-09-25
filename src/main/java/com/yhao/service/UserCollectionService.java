package com.yhao.service;

import com.yhao.domain.AlarmClockMapper;
import com.yhao.domain.UserCollectionMapper;
import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.AlarmClock;
import com.yhao.domain.entity.User;
import com.yhao.domain.entity.UserCollection;
import com.yhao.enums.ResultStatus;
import com.yhao.result.BaseResult;
import com.yhao.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 12:21
 **/
@Service
public class UserCollectionService {

    @Autowired
    private UserCollectionMapper userCollectionMapper;
    @Autowired
    private AlarmClockMapper alarmClockMapper;

    public BaseResult add(Integer userId, Integer alarmClockId) {
        UserCollection userCollection = userCollectionMapper.findByUserIdAndAlarmClockId(userId, alarmClockId);
        if (userCollection != null) {
            return BaseResult.failure(ResultStatus.HAD_CONLLECTION);
        }
        userCollectionMapper.insert(new UserCollection(userId, alarmClockId));
        return BaseResult.success(ResultStatus.COLLECTION_SUCCESS);
    }

    public List<AlarmClock> list(Integer userId) {
        List<Integer> alarmClockIds = userCollectionMapper.findAlarmClockIdByUserId(userId);
        return alarmClockMapper.findByIds(alarmClockIds);
    }
}
