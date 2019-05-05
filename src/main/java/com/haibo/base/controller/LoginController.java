package com.haibo.base.controller;

import com.haibo.base.entity.Teacher;
import com.haibo.base.entity.User;
import com.haibo.base.service.PoiService;
import com.haibo.base.service.ProductService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "downLoadExcel", method = RequestMethod.GET)
    public void downLoadExcel(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<Teacher> classmateList = new ArrayList<>();
        classmateList.add(new Teacher("1","张三","1","123456"));
        classmateList.add(new Teacher("2","李四","1","123456"));
        classmateList.add(new Teacher("3","王五","1","123456"));
        classmateList.add(new Teacher("4","赵六","1","123456"));
        classmateList.add(new Teacher("5","周八","1","123456"));

        String fileName = "userinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "学号", "姓名", "身份类型", "登录密码"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Teacher teacher : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getTno());
            row1.createCell(1).setCellValue(teacher.getTname());
            row1.createCell(2).setCellValue(teacher.getType());
            row1.createCell(3).setCellValue(teacher.getTpassword());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
