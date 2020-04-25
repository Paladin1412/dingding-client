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
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
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

    public static String httpGet(String url, Map<String, String> para) throws Exception {

        URIBuilder builder = new URIBuilder(url);
        if (para != null) {
            Set<String> set = para.keySet();
            for (String key : set) {
                builder.setParameter(key, para.get(key));
            }
        }

        HttpGet request = new HttpGet(builder.build());
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).setConnectionRequestTimeout(6000).build();
        request.setConfig(requestConfig);
        String uri = request.getURI().toString();
        System.out.println(request.getURI().toString());

        /* 1 生成 HttpClinet 对象并设置参数 */
        HttpClient httpClient = new HttpClient();
        // 设置 Http 连接超时为5秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        /* 2 生成 GetMethod 对象并设置参数 */
        GetMethod getMethod = new GetMethod(uri);
        // 设置 get 请求超时为 5 秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // 设置请求重试处理，用的是默认的重试处理：请求三次
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        String response = "";
        /* 3 执行 HTTP GET 请求 */
        try {
            int statusCode = httpClient.executeMethod(getMethod);
        /* 4 判断访问的状态码 */
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("请求出错: " + getMethod.getStatusLine());
            }
        /* 5 处理 HTTP 响应内容 */
            // HTTP响应头部信息，这里简单打印
            Header[] headers = getMethod.getResponseHeaders();
//            for (Header h : headers) {
//                System.out.println(h.getName() + "------------ " + h.getValue());
//            }
            // 读取 HTTP 响应内容，这里简单打印网页内容
            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
            response = new String(responseBody, "utf-8");
            logger.warn("response {}", response);
            // 读取为 InputStream，在网页内容数据量大时候推荐使用
            // InputStream response = getMethod.getResponseBodyAsStream();
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("请检查输入的URL!");
        } catch (IOException e) {
            // 发生网络异常
            System.out.println("发生网络异常!");
        } finally {
        /* 6 .释放连接 */
            getMethod.releaseConnection();
        }
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
        String url1 = "https://sso.ding.zj.gov.cn/api/bff/v1.2/developer/scim/organization/dir_children?access_token=7de20e38-5715-4181-9212-0e8b46e4dacf&externalId=2018bbb5119b4f9aa0a2cbbcc311cc2d";
        requestPost(url, "");
        httpGet(url1, null);
//        Map<String, String> map = new HashMap();
//        map.put("name", "after");
//        map.put("address", "suzhou");
//        String ret = httpGet(url, map);
//        System.out.println("response:\n" + ret);
    }

}
