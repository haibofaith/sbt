package com.haibo.base.mapper;

import com.haibo.base.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author:haibo.xiong
 * @date:2019/7/11
 * @description:
 */
@Mapper
public interface UserMapper {
    int insertUser(User user);
    User selectUserById(Integer id);
    int updateUser(User user);
    int deleteUser(Integer id);
}
