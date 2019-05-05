package com.haibo.base.controller;

import com.haibo.base.entity.User;
import com.haibo.base.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:haibo.xiong
 * @date:2019/4/25
 * @description:
 */
@RestController
public class LoginController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/")
    public String hello() {
        return "请登录";
    }

    private static User user = new User();

    static {
        user.setUserId(1);
        user.setUserName("XiongHaiBo");
    }


    @RequestMapping(value = "/user")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public User getUser() {
        return user;
    }

    @RequestMapping("/mysqlData")
    public ResponseEntity<Object> mysqlData() {

        return new ResponseEntity<>(productService.getList(), HttpStatus.OK);
    }

    @RequestMapping("/oracleData")
    public ResponseEntity<Object> oracleData() {
        return new ResponseEntity<>(productService.getSumSalary(), HttpStatus.OK);
    }

}
