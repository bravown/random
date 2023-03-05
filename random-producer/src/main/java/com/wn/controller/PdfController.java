package com.wn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lazada.lazop.api.LazopClient;
import com.lazada.lazop.api.LazopRequest;
import com.lazada.lazop.api.LazopResponse;
import com.lazada.lazop.util.ApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.*;

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
import java.util.Objects;
import java.util.Optional;

/**
 * 下载PDF到指定路径
 *
 * @author 王宁 2022/3/6
 */
@RestController
@RequestMapping("pdf")
public class PdfController {

    /**
     * 默认的PDF文件名字
     */
    private static final String defaultPdfName = "lazada.pdf";

    /**
     * 下载PDF到指定路径
     *
     * @param filePath 指定路径
     * @param resp
     */
    @PostMapping("/download")
    public void downloadLazadaPdf(@RequestBody JSONObject filePath, HttpServletResponse resp) throws InterruptedException, ApiException, IOException {

        if (Objects.isNull(filePath)) {
            throw new RuntimeException("请选择下载路径！");
        }

        // 接口数据
        String url = "https://api.lazada.com.my/rest";
        String appkey = "106910";
        String appSecret = "aJzraVsOcsMarXYJOXzheXirBVDUCXW9";
        String accessToken = "50000600a27NQw9iBNyqPg9YFYcKPauuEBdES6o1d68452aRyCmdNH4kuJC4omGs";

        // 调用接口
        LazopClient client = new LazopClient(url, appkey, appSecret);
        LazopRequest request = new LazopRequest();
        request.setApiName("/order/document/awb/pdf/get");
        request.setHttpMethod("GET");
        request.addApiParameter("order_item_ids", "[323438867703989]");
        LazopResponse response = client.execute(request, accessToken);
        Thread.sleep(10);

        // 处理结果
        String base64PdfUrl = getBase64PdfUrl(response.getBody());
        // 下载PDF
        downloadPdf(base64PdfUrl, filePath.getString("filePath"), resp);
    }

    /**
     * 解码lazada GetAwbDocumentHtml接口返回的base64字符串，并获取其中PDF文件的url
     *
     * @param lazadaReturn base64字符串
     */
    public String getBase64PdfUrl(String lazadaReturn) throws IOException {

        // 解码base64
        final Base64.Decoder decoder = Base64.getDecoder();
        // 获取base64的file字段中的值
        String base64String = getJsonAttribute(lazadaReturn);
        // 解码base64
        String base64Decode = new String(decoder.decode(base64String), "UTF-8");
        // 获取PDF的url
        Document doc = Jsoup.parseBodyFragment(base64Decode);
        // 得到标签内容
        Element element = doc.getElementsByTag("iframe").get(0);
        // 获取lazada返回参数的src属性的值
        return element.attr("src");
    }

    /**
     * 根据PDF的url，下载PDF到指定到路径
     */
    @GetMapping(value = "/download")
    public void downloadPdf(String url, String filePath, HttpServletResponse response) {

        if (Objects.isNull(url)) {
            return;
        }
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
            ips = uc.getInputStream();
            response.setContentType("multipart/form-data");
            //为文件重新设置名字，采用数据库内存储的文件名称
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(defaultPdfName.getBytes("UTF-8"), "ISO8859-1") + "\"");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];

            OutputStream outputStream = new FileOutputStream(filePath + "/" + defaultPdfName);

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
        return;
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

}
