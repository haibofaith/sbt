package com.haibo.base.entity;

import java.io.Serializable;

/**
 * @author:haibo.xiong
 * @date:2019/5/5
 * @description: row1.createCell(0).setCellValue(teacher.getTno());
 * row1.createCell(1).setCellValue(teacher.getTname());
 * row1.createCell(2).setCellValue(teacher.getType());
 * row1.createCell(3).setCellValue(teacher.getTpassword());
 */
public class Teacher implements Serializable {
    private String tno;
    private String tname;
    private String type;
    private String tpassword;

    public Teacher(String tno, String tname, String type, String tpassword) {
        this.tno = tno;
        this.tname = tname;
        this.type = type;
        this.tpassword = tpassword;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }
}
