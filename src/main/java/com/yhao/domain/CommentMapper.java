package com.yhao.domain;

import com.yhao.domain.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 14:41
 **/
@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM comment ORDER BY created_time DESC")
    List<Comment> findByTimeDesc();

    @Select("SELECT * FROM comment ORDER BY created_time ASC")
    List<Comment> findByTimeAsc();

    @Select("SELECT * FROM comment ORDER BY star DESC")
    List<Comment> findByStarDesc();

    @Select("SELECT * FROM comment ORDER BY star ASC")
    List<Comment> findByStarAsc();

    @Select("SELECT * FROM comment ORDER BY count DESC")
    List<Comment> findByCountDesc();

    @Select("SELECT * FROM comment ORDER BY count DESC")
    List<Comment> findByCountAsc();

    @Insert("INSERT INTO comment(user_id,picture,data,created_time,updated_time) VALUES(#{userId},#{picture},#{data},current_timestamp(),current_timestamp())")
    int insert(Comment comment);

    @Select("SELECT * FROM comment WHERE id = #{id}")
    Comment findById(Integer id);

    @Update("UPDATE comment SET user_id=#{userId}, count=#{count}, picture=#{picture}, data=#{data}, star=#{star}, updated_time=current_timestamp() WHERE id=#{id}")
    int update(Comment comment);
}
