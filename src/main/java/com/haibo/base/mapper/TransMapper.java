package com.haibo.base.mapper;

import com.haibo.base.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransMapper {
    List<User> selectUser();
    Integer insertUser(@Param("user") User user);
}
