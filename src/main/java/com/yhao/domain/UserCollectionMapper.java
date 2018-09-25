package com.yhao.domain;

import com.yhao.domain.entity.User;
import com.yhao.domain.entity.UserCollection;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 14:41
 **/
@Mapper
public interface UserCollectionMapper {

    @Select("SELECT * FROM user_collection WHERE user_id = #{userId} AND alarm_clock_id = #{alarmClockId}")
    UserCollection findByUserIdAndAlarmClockId(@Param("userId") Integer userId,@Param("alarmClockId") Integer alarmClockId);

    @Delete("DELETE FROM user_collection WHERE id =#{id}")
    void delete(Integer id);


    @Insert("INSERT INTO user_collection(user_id, alarmClock_id) VALUES(#{userId}, #{alarmClockId})")
    int insert(UserCollection userCollection);

    @Select("SELECT * FROM user_collection WHERE user_id = #{userId}")
    List<UserCollection> findByUserId(@Param("userId")Integer userId);

    @Select("SELECT alarm_clock_id FROM user_collection WHERE user_id = #{userId}")
    List<Integer> findAlarmClockIdByUserId(@Param("userId")Integer userId);
}
