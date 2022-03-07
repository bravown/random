package com.wn.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class HtmlController {

    /**
     * 解码base64并生成html到指定路径
     *
     * @param base64String
     * @param filePath
     * @throws UnsupportedEncodingException
     */
    public Boolean base64ToHtml(String base64String, String filePath) throws UnsupportedEncodingException {

        // 解码base64
        final Base64.Decoder decoder = Base64.getDecoder();
        String htmlString = new String(decoder.decode(base64String), "UTF-8");

        StringBuilder sb = new StringBuilder();
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream(filePath + "/lazada.html")); //路径默认在项目根目录下
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sb.append(htmlString);
        printStream.println(sb);
        return true;
    }
}
