package com.haibo.base.controller;

import com.haibo.base.entity.Result;
import com.haibo.base.entity.User;
import com.haibo.base.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/7/12
 * @description:
 */
@RestController
public class MySqlController {
    @Autowired
    private TransService transService;

    @RequestMapping(value = "/transaction")
    @ResponseBody
    public Result transactionController(){
        return transService.testTransaction();
    }

}
