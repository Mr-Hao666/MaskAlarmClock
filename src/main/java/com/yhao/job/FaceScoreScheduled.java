package com.yhao.job;

import com.yhao.domain.FaceScoreMapper;
import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.FaceScore;
import com.yhao.domain.entity.User;
import com.yhao.service.FaceScoreService;
import com.yhao.util.Colls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-20 12:06
 **/
@Slf4j
@Component
@Async
public class FaceScoreScheduled {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FaceScoreService faceScoreService;

    @Autowired
    private FaceScoreMapper faceScoreMapper;

    /**
     * 判断每天没有按时保养的用户降分
     */
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduled125() {

        int index = 0;
        int size = 100;
        long time = System.currentTimeMillis();
        List<User> userList = userMapper.findLimit(index, size);

        while (Colls.notEmpty(userList)) {
            for (User user : userList) {
                int style1 = 0;
                int style2 = 0;
                int style5 = 0;
                List<FaceScore> faceScoreList = faceScoreMapper.findByUserIdAndTypeYesterday(user.getId(),1);
                for (FaceScore faceScore : faceScoreList) {
                    switch (faceScore.getStyle()) {
                        case 1:
                            style1 += 1;
                            break;
                        case 2:
                            style2 += 1;
                            break;
                        case 5:
                            style5 += 1;
                            break;
                        default:
                            break;
                    }
                }
                if (style1 < 1) {
                    faceScoreService.reduceStyle1(user.getId());
                }
                if (style2 < 1) {
                    faceScoreService.reduceStyle2(user.getId());
                }
                if (style5 < 1) {
                    faceScoreService.reduceStyle5(user.getId());
                }
            }
            userList = userMapper.findLimit(index + size, size);
        }

        log.info("=====>>>>>颜值（补水、美白、闹钟）指数操作完成{}", new Date(time).toString());
    }

    /**
     * 判断每天没有按时保养的用户降分
     */
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduled34() {

        int index = 0;
        int size = 100;
        long time = System.currentTimeMillis();
        List<User> userList = userMapper.findLimit(index, size);

        while (Colls.notEmpty(userList)) {
            for (User user : userList) {
                int style3 = 0;
                int style4 = 0;
                List<FaceScore> faceScoreList = faceScoreMapper.findByUserIdAndType7DayEarly(user.getId(),1);
                for (FaceScore faceScore : faceScoreList) {
                    switch (faceScore.getStyle()) {
                        case 1:
                            style3 += 1;
                            break;
                        case 2:
                            style4 += 1;
                            break;
                        default:
                            break;
                    }
                }
                if (style3 < 1) {
                    faceScoreService.reduceStyle3(user.getId());
                }
                if (style4 < 1) {
                    faceScoreService.reduceStyle4(user.getId());
                }
            }
            userList = userMapper.findLimit(index + size, size);
        }

        log.info("=====>>>>>颜值(紧致、滋润)指数操作完成{}", new Date(time).toString());
    }


}
