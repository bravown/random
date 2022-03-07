//package com.wn.controller;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.Base64;
//
///**
// * @author 王宁 2022/3/6
// */
//@RestController
//@RequestMapping("file")
//public class FileController {
//
//    @PostMapping("/download")
//    public void convertForStream(HttpServletResponse response) throws IOException {
//        try {
//            String htmlString = decodeBase64();
//            createHtmlFile(htmlString);
//            StringBuilder stringBuilder = new StringBuilder();
//            base64ToPdf("PGlmcmFtZSBzcmM9Imh0dHBzOi8vc2VsbGVyY2VudGVyLmxhemFkYS5jb20ubXkvb3NzL3Byb3h5L3dheWJpbGxwcmludGJ1Y2tldC5vc3MtYXAtc291dGhlYXN0LTEuYWxpeXVuY3MuY29tL1BERi9iMzBiNTlhNi1kYWJkLTQ5MzctOGRkOS1lZTdlM2I3MjE5OWQ/RXhwaXJlcz0xNjQ2NTc5NTIxJk9TU0FjY2Vzc0tleUlkPVRNUC4zS2ROdXM2RXdUbkgxQmVDWVNXTjdvRGtWRmFNcDNSQm1TSlZTTDJuakxCQVE5TXljYkpaMVB5NFpqYUZNVG1zdGNNWGo2RFNqOGo0SE10WWt1YkhnbXFFZm1IQTZBJlNpZ25hdHVyZT1YRXZoemZhZlVoYUVSN1RiRTRUUmE4bGRZZm8lM0QiIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHN0eWxlPSJkaXNwbGF5OiBibG9jazttaW4taGVpZ2h0OiA5OTBweDsiPjwvaWZyYW1lPg==",
//                    "D:\\迅雷下载\\report.pdf");
//            base64ToPng(stringBuilder,
//                    "D:\\迅雷下载\\png.png");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String decodeBase64() throws UnsupportedEncodingException {
//        // 解码base64
//        String base64 = "";
//        final Base64.Decoder decoder = Base64.getDecoder();
//        String html = new String(decoder.decode(base64), "UTF-8");
//        return html;
//    }
//
//    public PrintStream createHtmlFile(String htmlString) {
//        StringBuilder sb = new StringBuilder();
//        PrintStream printStream = null;
//        try {
//            printStream = new PrintStream(new FileOutputStream("D:\\迅雷下载\\report.html")); //路径默认在项目根目录下
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        sb.append(htmlString);
//        printStream.println(sb);
//        return printStream;
//    }
//
//    public void base64ToPdf(String base64String, String filePath) throws IOException {
//        // 解码base64
//        String base64 = base64String;
//        final Base64.Decoder decoder = Base64.getDecoder();
//        String pdf = new String(decoder.decode(base64), "UTF-8");
//        String s = pdf.split("src=\"")[1].split("\" width")[0];
//        System.out.println(s);
//
//    }
//
//    public boolean base64ToPng(StringBuilder baseStr, String path) {
//
//        if (baseStr == null) {
//            // 图像数据为空
//            return false;
//        }
//        BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            // Base64解码
//            byte[] b = decoder.decodeBuffer(baseStr.toString());
//            for (int i = 0; i < b.length; ++i) {
//                if (b[i] < 0) {// 调整异常数据
//                    b[i] += 256;
//                }
//            }
//            // 生成jpeg图片
//            String imgFilePath = path;// 新生成的图片
//            OutputStream out = new FileOutputStream(imgFilePath);
//            out.write(b);
//            out.flush();
//            out.close();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//
//}