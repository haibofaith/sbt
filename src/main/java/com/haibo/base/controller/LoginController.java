package com.haibo.base.controller;

import com.haibo.base.entity.User;
import com.haibo.base.service.PoiService;
import com.haibo.base.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private PoiService poiService;

    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        System.out.println(LoginController.class.getResource("").getPath());
        return poiService.fileUpload(file);
    }

}
