package com.haibo.base.service.impl;

import com.haibo.base.entity.Result;
import com.haibo.base.entity.User;
import com.haibo.base.mapper.TransMapper;
import com.haibo.base.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/7/26
 * @description:
 * 参考https://blog.csdn.net/justry_deng/article/details/80828180
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class TransServiceImpl implements TransService{
    @Autowired
    private TransMapper transMapper;

    @Override
    public Result testTransaction() {
        User user = new User();
        user.setId(18);
        user.setName("testTrans");
        user.setSex("M");
        User user2 = new User();
        user2.setId(19);
        user2.setName("testTrans2");
        user2.setSex("F");
        transMapper.insertUser(user);
        transMapper.insertUser(user2);
        List<User> users = transMapper.selectUser();
        Result result = new Result();
        result.setResult(users);
        return result;
    }
}
