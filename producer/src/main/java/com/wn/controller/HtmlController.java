package com.wn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;

public class HtmlController {

    /**
     * 获取lazada返回的base64字符串
     *
     * @param jsonString json字符串
     * @return base64字符串
     */
    public String getJsonAttribute(String jsonString) {

        JSONObject jsonObj = JSON.parseObject(jsonString);

        // 报文获取file
        return Optional.ofNullable(jsonObj)
                .flatMap(jsonObject -> Optional.ofNullable(jsonObject.getJSONObject("data")))
                .flatMap(jsonObject -> Optional.ofNullable(jsonObject.getJSONObject("document")))
                .flatMap(jsonObject -> Optional.ofNullable(jsonObject.getString("file")))
                .orElse(null);
    }


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
            printStream = new PrintStream(new FileOutputStream(filePath + "/lazada.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sb.append(htmlString);
        printStream.println(sb);
        return true;
    }
}
