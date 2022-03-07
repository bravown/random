package com.wn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lazada.lazop.api.LazopClient;
import com.lazada.lazop.api.LazopRequest;
import com.lazada.lazop.api.LazopResponse;
import com.lazada.lazop.util.ApiException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("html")
public class HtmlController {

    @RequestMapping("download")
    public Boolean downloadLazadaHtml(String filePath) throws InterruptedException, ApiException, UnsupportedEncodingException {

        String appkey = "100132";
        String appSecret = "BC9A632E5E937E6686DBE82348423DF491940AFE690F16E58AE5B6A9E59BD0A7";
        String url = "https://api.lazada.com.my/rest";
        String accessToken = "50000101b22qjsqHXEfpfLxVCGHNiWfRT91f4e2526E3HTtgwsaPlMy8h7EYk";

        LazopClient client = new LazopClient(url, appkey, appSecret);
        LazopRequest request = new LazopRequest();
        request.setApiName("/order/document/awb/html/get");
        request.setHttpMethod("GET");
        request.addApiParameter("order_item_ids", "[324649432343475,324649432443475]");
        LazopResponse response = client.execute(request, accessToken);
        Thread.sleep(10);

        String jsonAttribute = getJsonAttribute(response.getBody());

        return downloadHtml(jsonAttribute, filePath);
    }


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
    public Boolean downloadHtml(String base64String, String filePath) throws UnsupportedEncodingException {

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
