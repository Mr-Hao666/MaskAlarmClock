package com.yhao.domain;

import com.yhao.domain.entity.AlarmClock;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 14:41
 **/
@Mapper
public interface AlarmClockMapper {

    @Select("SELECT * FROM alarm_clock ORDER BY id DESC")
    List<AlarmClock> findAll();

    @Insert("INSERT alarm_clock(name_ch,name_en,icon,background,music)VALUES(#{nameCh},#{nameEn},#{icon},#{background},#{music})")
    int insert(AlarmClock alarmClock);

    @Select("SELECT * FROM alarm_clock WHERRE id IN(#{alarmClockIds})")
    List<AlarmClock> findByIds(@Param("alarmClockIds") List<Integer> alarmClockIds);
}
