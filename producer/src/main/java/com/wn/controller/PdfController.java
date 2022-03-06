package com.wn.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 王宁 2022/3/6
 */
@RestController
@RequestMapping("pdf")
public class PdfController {

    @GetMapping(value = "/cxfKp/download")
    public void invoiceDownload(String url, HttpServletResponse response) throws UnsupportedEncodingException {

        ServletOutputStream out = null;
        InputStream ips = null;
        URL oracle = null;
        url = "https://sellercenter.lazada.com.my/oss/proxy/waybillprintbucket.oss-ap-southeast-1.aliyuncs.com/PDF/b30b59a6-dabd-4937-8dd9-ee7e3b72199d?Expires=1646579521&OSSAccessKeyId=TMP.3KdNus6EwTnH1BeCYSWN7oDkVFaMp3RBmSJVSL2njLBAQ9MycbJZ1Py4ZjaFMTmstcMXj6DSj8j4HMtYkubHgmqEfmHA6A&Signature=XEvhzfafUhaER7TbE4TRa8ldYfo%3D";
        try {
            oracle = new URL(url);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return;
        }
        HttpURLConnection uc = null;
        try {
            uc = (HttpURLConnection) oracle.openConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        try {
            uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true
            uc.connect();
            //文件名
            String newFileName = fileName(url);
            //newFileName="电子发票.pdf";//重命名电子发票
            ips = uc.getInputStream();
            response.setContentType("multipart/form-data");
            //为文件重新设置名字，采用数据库内存储的文件名称
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(newFileName.getBytes("UTF-8"), "ISO8859-1") + "\"");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];


            OutputStream shuchu = new FileOutputStream("D:\\迅雷下载\\report.pdf");

            while ((len = ips.read(buffer)) != -1) {
//                out.write(buffer, 0, len);
                shuchu.write(buffer,0,len);

            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;

    }

    /**
     * 获取文件名字
     *
     * @param fileName
     * @return
     */
    private String fileName(String fileName) {
        String ext = null;
        if (StringUtils.isNotBlank(fileName)) {
            int offset = fileName.lastIndexOf("/");
            if (offset != -1 && offset != fileName.length() - 1) {
                ext = fileName.substring(offset + 1);
            }
        }
        return ext.toLowerCase();
    }
}
