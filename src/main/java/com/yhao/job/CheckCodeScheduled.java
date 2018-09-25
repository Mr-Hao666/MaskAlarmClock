package com.yhao.job;

import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.User;
import com.yhao.util.Colls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-17 17:43
 **/
@Slf4j
@Component
@Async
public class CheckCodeScheduled {

    @Autowired
    private UserMapper users;

    @Scheduled(cron = "0 0/1 * * * *")
    public void scheduled() {

        int index = 0;
        int size = 100;
        long time = users.getNow().getTime();
        List<User> userList = users.findLimit(index ,size);

        while (Colls.notEmpty(userList)) {
            for (User user : userList) {
                long distence = time - user.getUpdatedTime().getTime();
                if (user.getCheckCode() != null && distence > 1000 * 60 * 2) {
                    user.setCheckCode(null);
                    users.update(user);
                }
            }
            userList = users.findLimit(index+size ,size);
        }


        log.info("=====>>>>>清除checkCode {}", new Date(time).toString() );
    }
}
