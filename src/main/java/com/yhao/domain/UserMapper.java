package com.yhao.domain;

import com.yhao.domain.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User findByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user LIMIT #{index}, #{size}")
    List<User> findLimit(@Param("index") Integer index, @Param("size") Integer size);

    @Insert("INSERT INTO user(phone, name, sex, check_code,created_time,updated_time) VALUES(#{phone}, #{name}, #{sex}, #{checkCode},current_timestamp(),current_timestamp())")
    int insert(@Param("phone") String phone,@Param("name") String name, @Param("sex") Integer sex, @Param("checkCode") String checkCode);

    @Update("UPDATE user SET point=#{point}, check_code=#{checkCode}, name=#{name},icon=#{icon}, sex=#{sex},updated_time=current_timestamp() WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Integer id);


    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insertByUser(User user);


    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Update("UPDATE user SET icon=#{icon}, updated_time=current_timestamp() WHERE id=#{id}")
    void updateIcon(@Param("id")Integer userId, @Param("icon") String icon);

    @Select("select NOW()")
    Date getNow();
}