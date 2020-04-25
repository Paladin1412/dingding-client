package com.alibaba.hj.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hongjian.chen on 2017/9/29.
 */
public class HttpClientUtil {

    public static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String httpGet(String url) throws Exception {
        String response = "";
        // 获取http客户端
        CloseableHttpClient client = HttpClients.createDefault();
        // 通过httpget方式来实现我们的get请求
        HttpGet httpGet = new HttpGet(url);
        // 通过client调用execute方法，得到我们的执行结果就是一个response，所有的数据都封装在response里面了
        CloseableHttpResponse Response = client.execute(httpGet);
        // HttpEntity
        // 是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
        // 所有的响应的数据，也全部都是封装在HttpEntity里面
        HttpEntity entity = Response.getEntity();
        // 通过EntityUtils 来将我们的数据转换成字符串
        String str = EntityUtils.toString(entity, "UTF-8");
        // EntityUtils.toString(entity)
        logger.warn("result{}", str);
        // 关闭
        Response.close();
        return response;
    }

    public static String requestPost(String uri, String params) {
        String result = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(uri);
        InputStream in = null;
        BufferedReader br = null;
        try {
            RequestEntity requestEntity = new StringRequestEntity(params, "application/json", "utf-8");
            postMethod.setRequestEntity(requestEntity);
            httpClient.executeMethod(postMethod);
            in = postMethod.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(in));
            String str = br.readLine();
            result = new String(str.getBytes("utf-8"), "utf-8");
            logger.error("result {}", result);
        } catch (Exception e) {
            logger.error("error {}", e.getMessage());
        } finally {
            try {
                br.close();
                in.close();
            } catch (IOException e) {
                logger.error("error {}", e.getMessage());
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String url = "https://sso.ding.zj.gov.cn/oauth/token?client_id=be43d4a821e9e13e7735c164380ed859IQsDk4vfxjs&client_secret=Cl8XXhcDEIrSsC3IoDrDsA1LNiW60LO2V5px2D25tn&scope=read&grant_type=client_credentials";
        String url1 = "https://sso.ding.zj.gov.cn/api/bff/v1.2/developer/scim/organization/dir_children?access_token=90ab8511-7d78-45d2-9539-6f1e936b6728&externalId=2018bbb5119b4f9aa0a2cbbcc311cc2d";
        String url2="https://oapi-dingtalk-pub-pro.ding.zj.gov.cn/user/getuserinfo?access_token=app_f4e6836b6908488fb3959c27394b2e6c&code=231162f0162943ceb7f0a3939bf4d300b50c0c01";
//        requestPost(url, "");
        httpGet(url1);
//        Map<String, String> map = new HashMap();
//        map.put("name", "after");
//        map.put("address", "suzhou");
//        String ret = httpGet(url, map);
//        System.out.println("response:\n" + ret);
    }

}
