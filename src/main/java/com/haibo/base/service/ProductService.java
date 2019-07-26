package com.haibo.base.service;

import com.haibo.base.entity.User;
import com.haibo.base.entity.UserInfo;

import java.util.List;

public interface ProductService {
    List<UserInfo> getList();

    Integer getSumSalary();
}
