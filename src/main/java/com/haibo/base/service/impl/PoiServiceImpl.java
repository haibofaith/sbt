package com.haibo.base.service.impl;

import com.haibo.base.utils.Utils;
import com.haibo.base.service.PoiService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author:haibo.xiong
 * @date:2019/5/5
 * @description:
 */
@Service
public class PoiServiceImpl implements PoiService{
    @Override
    public ResponseEntity fileUpload(MultipartFile file) {
        if (!Utils.checkExtension(file)){
            return new ResponseEntity("请求文件类型错误:后缀名错误",HttpStatus.BAD_REQUEST);
        }
        try {
            if (Utils.isOfficeFile(file)){
                //正确的文件类型 自动判断2003或者2007
                Workbook workbook = Utils.getWorkbookAuto(file);
                Sheet sheet = workbook.getSheetAt(0);//默认只有一个sheet
                int rows = sheet.getPhysicalNumberOfRows();//获得sheet有多少行
                //读第一个sheet
                for (int i = 0;i<rows;i++){
                    Row row = sheet.getRow(i);
                    for (int j=0;j<row.getLastCellNum();j++){
                        Cell cell = row.getCell(j);
                        if (cell!=null)
                        System.out.println(cell.toString());
                    }
                }
            }else {
                return new ResponseEntity("请求文件类型错误:文件类型错误",HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
