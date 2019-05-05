package com.haibo.base.Utils;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author:haibo.xiong
 * @date:2019/5/5
 * @description:
 */
public class Utils {
    /**
     * 判断是否office文件
     * @param inputStream
     * @return
     */
    public static Boolean isOfficeFile(InputStream inputStream){
        boolean result = false;
        try {
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if (Objects.equals(fileMagic,FileMagic.OLE2)||Objects.equals(fileMagic,fileMagic.OOXML)){
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断是否office文件
     * @param file
     * @return
     * @throws IOException
     */
    public static Boolean isOfficeFile(MultipartFile file) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(file.getInputStream());
        boolean result = false;
        result = isOfficeFile(bufferedInputStream);
        return result;
    }

    /**
     * 判断扩展名是否是excel扩展名
     * @param extension
     * @return
     */
    public static Boolean checkExtension(String extension){
        return Lists.newArrayList("xls","xlsx","XLS","XLSX").contains(extension);
    }

    /**
     * 判断扩展名是否是excel扩展名
     * @param file
     * @return
     */
    public static Boolean checkExtension(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);
        return checkExtension(extension);
    }

    public static Workbook getWorkbookAuto(MultipartFile file) throws IOException {
        /** 判断文件的类型，是2003还是2007 */
        boolean isExcel2003 = true;
        if (isExcel2007(file.getOriginalFilename())) {
            isExcel2003 = false;
        }
        BufferedInputStream is = new BufferedInputStream(
                file.getInputStream());
        Workbook wb;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        return wb;
    }

    public static Workbook getReadExcelWorkbook(String filePath) {
        try {
            /** 验证文件是否合法 */
            if (!validateExcel(filePath)) {
                return null;
            }
            /** 判断文件的类型，是2003还是2007 */
            boolean isExcel2003 = true;
            if (isExcel2007(filePath)) {
                isExcel2003 = false;
            }

            /** 调用本类提供的根据流读取的方法 */
            File file = new File(filePath);
            BufferedInputStream is = new BufferedInputStream(
                    new FileInputStream(file));
            Workbook wb;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }

            return wb;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void writeExcel(Workbook wb, String filePath) {
        File file = new File(filePath);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeTxt(String filePath, List<String> context) {
        File file1 = new File(filePath);
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fileWritter	;
        BufferedWriter bufferWritter = null;
        try {
            fileWritter = new FileWriter(file1);
            bufferWritter = new BufferedWriter(fileWritter);
            for (String s : context) {
                bufferWritter.write(s);
                bufferWritter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferWritter.flush();
                bufferWritter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("over");
    }

    private static boolean validateExcel(String filePath) {

        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null
                || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }

        /** 检查文件是否存在 */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    public static String getTmpFilePath(){
        String current = Utils.class.getResource("/").getPath();
        try {
            current = URLDecoder.decode(current, "utf-8");
            Properties pro = System.getProperties();
            String osNameString = pro.getProperty("os.name");
            if (!osNameString.equalsIgnoreCase("linux")&&!osNameString.toUpperCase().startsWith("MAC")) {
                current = current.substring(1);
            }
        } catch (Exception e) {
        }
        return current;
    }


    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
