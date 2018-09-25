package com.yhao.domain;

import com.yhao.domain.entity.FaceScore;
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
public interface FaceScoreMapper {

    @Insert("INSERT INTO face_score(user_id,style,num,type,score,created_time,updated_time) VALUES(#{userId},#{style},#{num},#{type},#{score},current_timestamp(),current_timestamp())")
    int insert(FaceScore faceScore);

    @Select("SELECT * FROM face_score WHERE user_id = #{userId} AND type = #{type} AND date(created_time) = date_sub(now(),interval 1 day)")
    List<FaceScore> findByUserIdAndTypeYesterday(@Param("userId") Integer userId,@Param("type")Integer type);

    @Select("SELECT * FROM face_score WHERE user_id = #{userId} AND type = #{type} AND date(created_time) BETWEEN date_sub(curdate(),interval 7 day) AND date_sub(now(),interval 1 day) ")
    List<FaceScore> findByUserIdAndType7DayEarly(@Param("userId") Integer userId,@Param("type")Integer type);

    @Select("SELECT * FROM face_score WHERE user_id = #{userId} AND to_days(created_time) = to_days(now())")
    List<FaceScore> findByUserIdToday(@Param("userId") Integer userId);

    @Select("SELECT * FROM face_score WHERE user_id = #{userId} AND style = #{style} AND to_days(created_time) = to_days(now())")
    List<FaceScore> findByUserIdAndStyleToday(@Param("userId")Integer userId, @Param("style") Integer style);
}
