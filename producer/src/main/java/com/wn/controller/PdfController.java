package com.wn.controller;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * @author 王宁 2022/3/6
 */
@RestController
@RequestMapping("pdf")
public class PdfController {

    public Boolean downloadLazadaPdf(String filePath, HttpServletResponse response) throws IOException, InterruptedException {
        String aa = "27df6b30502b2fd457864fbaaced6c054c90b5a6c92e59b0dfcbe7f145588465";

        long timestamp = (new Date().getTime()) / 1000;

        String url = "https://partner.shopeemobile.com/api/v1/logistics/forder_waybill/get_mass";

        String body = "{\"partner_id\":2003284,\"timestamp\":" + timestamp + ",\"orders_list\":[\"ordersn\":\"220228MY6DR5H5\",\"package_number\":\"# TL5547917843\"],\"shopid\":96947910}";


        String method = "POST";
        Mac mac = HmacUtils.getInitializedMac("HmacSHA256", aa.getBytes(StandardCharsets.UTF_8));
        mac.reset();
        String sign = Hex.encodeHexString(mac.doFinal((url + '|' + body).getBytes(StandardCharsets.UTF_8)));
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url));
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8);
        HttpRequest request = builder.header("Content-Type", "application/json").header("Authorization", sign)
                .method(method, bodyPublisher).build();
        HttpClient.Builder clientBuilder = HttpClient.newBuilder();
        HttpClient client = clientBuilder.version(HttpClient.Version.HTTP_1_1).build();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        String base64PdfUrl = getBase64PdfUrl(resp.body(), filePath, response);
        return downloadPdf(base64PdfUrl, filePath, response);
    }

    /**
     * 解码lazada GetAwbDocumentHtml接口返回到base64字符串，并获取其中的pdf文件url
     *
     * @param base64String base64字符串
     * @param filePath     下载到到路径
     * @throws IOException
     */
    public String getBase64PdfUrl(String base64String, String filePath, HttpServletResponse response) throws IOException {

        // 解码base64
        final Base64.Decoder decoder = Base64.getDecoder();
        String lazadaReturn = new String(decoder.decode(base64String), "UTF-8");

        Document doc = Jsoup.parseBodyFragment(lazadaReturn);
        //得到标签内容
        Element element = doc.getElementsByTag("iframe").get(0);
        // 获取lazada返回参数的src属性的值
        return element.attr("src");
    }

    /**
     * 根据pdf url，下载pdf到指定到路径
     */
    @GetMapping(value = "/download")
    public Boolean downloadPdf(String url, String filePath, HttpServletResponse response) {

        ServletOutputStream out = null;
        InputStream ips = null;
        URL pdfUrl = null;
        try {
            pdfUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        HttpURLConnection uc = null;
        try {
            uc = (HttpURLConnection) pdfUrl.openConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
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
        return true;
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

}
