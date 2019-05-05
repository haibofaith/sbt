package com.haibo.base.service;

import com.haibo.base.entity.User;

import java.util.List;

public interface ProductService {
    List<User> getList();

    Integer getSumSalary();
}
