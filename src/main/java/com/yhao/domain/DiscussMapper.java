package com.yhao.domain;

import com.yhao.domain.entity.Discuss;
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
public interface DiscussMapper {

    @Select("SELECT * FROM discuss WHERE comment_id = #{commentId} ORDER BY id DESC")
    List<Discuss> findByCommentId(@Param("commentId") Integer commentId);

    @Insert("INSERT INTO discuss(comment_id,user_id,icon,name,star,remark,created_time,updated_time) VALUES(#{commentId},#{userId},#{icon},#{name},#{star},#{remark},current_timestamp(),current_timestamp()")
    void insert(Discuss discuss);
}
