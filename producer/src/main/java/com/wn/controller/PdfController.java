package com.wn.controller;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * @author 王宁 2022/3/6
 */
@RestController
@RequestMapping("pdf")
public class PdfController {

    /**
     * 根据pdf url，下载pdf到指定到路径
     */
    @GetMapping(value = "/download")
    public void pdfDownload(String url, String filePath, HttpServletResponse response) {

        ServletOutputStream out = null;
        InputStream ips = null;
        URL pdfUrl = null;
        try {
            pdfUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        HttpURLConnection uc = null;
        try {
            uc = (HttpURLConnection) pdfUrl.openConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        try {
            uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true
            uc.connect();
            //文件名
            String newFileName = fileName(url);
            ips = uc.getInputStream();
            response.setContentType("multipart/form-data");
            //为文件重新设置名字，采用数据库内存储的文件名称
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(newFileName.getBytes("UTF-8"), "ISO8859-1") + "\"");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];

            OutputStream outputStream = new FileOutputStream(filePath + "/lazada.pdf");

            while ((len = ips.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();

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
    }

    /**
     * 获取文件名字
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

    /**
     * 解码lazada GetAwbDocumentHtml接口返回到base64字符串，并获取其中的pdf文件url
     *
     * @param base64String base64字符串
     * @param filePath     下载到到路径
     * @throws IOException
     */
    public void base64ToPdf(String base64String, String filePath, HttpServletResponse response) throws IOException {

        // 解码base64
        final Base64.Decoder decoder = Base64.getDecoder();
        String lazadaReturn = new String(decoder.decode(base64String), "UTF-8");

        Document doc = Jsoup.parseBodyFragment(lazadaReturn);
        //得到标签内容
        Element element = doc.getElementsByTag("iframe").get(0);
        // 获取lazada返回参数的src属性的值
        String lazadaPdfUrl = element.attr("src");

        // 下载pdf文件到指定路径
        pdfDownload(lazadaPdfUrl, filePath, response);
    }
}
