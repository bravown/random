package com.wn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lazada.lazop.api.LazopClient;
import com.lazada.lazop.api.LazopRequest;
import com.lazada.lazop.api.LazopResponse;
import com.lazada.lazop.util.ApiException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/html")
public class HtmlController {

    /**
     * 默认的HTML文件名字
     */
    private static final String defaultHtmlName = "lazada.html";

    /**
     * 下载HTML文件到指定路径
     *
     * @param filePath 下载路径json
     */
    @PostMapping("/download")
    public Boolean downloadLazadaHtml(@RequestBody JSONObject filePath) throws InterruptedException, ApiException, UnsupportedEncodingException {

        if (Objects.isNull(filePath)) {
            return false;
        }

        // 调用接口数据
        String url = "https://api.lazada.com.my/rest";
        String appkey = "106910";
        String appSecret = "aJzraVsOcsMarXYJOXzheXirBVDUCXW9";
        String accessToken = "50000600a27NQw9iBNyqPg9YFYcKPauuEBdES6o1d68452aRyCmdNH4kuJC4omGs";

//        LazopClient client = new LazopClient(url, appkey, appSecret);
//        LazopRequest request = new LazopRequest();
//        request.setApiName("/order/document/awb/html/get");
//        request.setHttpMethod("GET");
//        request.addApiParameter("order_item_ids", "[323438867703989]");
//        LazopResponse response = client.execute(request, accessToken);
//        Thread.sleep(10);

        // 调用lazada接口
        LazopClient client = new LazopClient(url, appkey, appSecret);
        LazopRequest request = new LazopRequest();
        request.setApiName("/order/document/get");
        request.setHttpMethod("GET");
        request.addApiParameter("doc_type", "shippingLabel");
        request.addApiParameter("order_item_ids", "[323438867703989]");
        LazopResponse response = client.execute(request, accessToken);
        System.out.println(response.getBody());
        Thread.sleep(10);


        // 获取base64字符串
        String jsonAttribute = getJsonAttribute(response.getBody());

        // 下载html
        return downloadHtml(jsonAttribute, filePath.getString("filePath"));
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
     * 解码base64并生成HTML文件到指定路径
     *
     * @param base64String
     * @param filePath
     */
    public Boolean downloadHtml(String base64String, String filePath) throws UnsupportedEncodingException {

        if (Objects.isNull(base64String)) {
            return false;
        }
        // 解码base64
        final Base64.Decoder decoder = Base64.getDecoder();
        String htmlString = new String(decoder.decode(base64String), "UTF-8");

        StringBuilder sb = new StringBuilder();
        PrintStream printStream = null;
        try {
            // 创建空HTML文件
            printStream = new PrintStream(new FileOutputStream(filePath + defaultHtmlName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sb.append(htmlString);
        // 写入HTML代码
        printStream.println(sb);
        return true;
    }
}
