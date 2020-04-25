package com.alibaba.hj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.GetClient;
import com.alibaba.xxpt.gateway.shared.client.http.PostClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallTestController {
    /**
     * 获取用户信息
     */
    @GetMapping("/getuserInfo")
    public
    @ResponseBody
    String getUserInfo(String code) {
        try {
            //================初始化开放平台sdk================
            ExecutableClient executableClient = ExecutableClient.getInstance();
            executableClient.setAccessKey("remote-test-0P199w3mEouPVBBnae");
            executableClient.setSecretKey("7dc7k0fDMG0h0kJnl3r75S5Cbts4NxNk6M69GU63");
            executableClient.setDomainName("openplatform-open.alibaba-inc.com");//公有云
//            executableClient.setDomainName("openplatform-pro.ding.zj.gov.cn");//专有云
            executableClient.setProtocal("https");
            executableClient.init();

            //================获取应用accesstoken================
            GetClient getClient = executableClient.newGetClient("/gettoken.json");
            //设置参数
//            getClient.addParameter("appkey", "remote-test-0P199w3mEouPVBBnae");
//            getClient.addParameter("appsecret", "7dc7k0fDMG0h0kJnl3r75S5Cbts4NxNk6M69GU63");
            //调用API
            String appTokenResult = getClient.get();
            JSONObject appTokenResultJSON = JSONObject.parseObject(appTokenResult);
            if ("false".equals(appTokenResultJSON.getString("success"))) {
                throw new Exception("get apptoken failed" + appTokenResultJSON.getJSONObject("content").getBoolean("responseMessage"));
            }
            String appToken = appTokenResultJSON.getJSONObject("content").getJSONObject("data").getString("accessToken");
            System.out.println("appTokenResult=" + appTokenResult);
            //================获取用户信息================
            PostClient postClient = executableClient.newPostClient("/rpc/oauth2/dingtalk_app_user.json");
            postClient.addParameter("access_token", appToken);
            postClient.addParameter("auth_code", code);
            String userInfoResult = postClient.post();
            JSONObject userInfoResultJSON = JSONObject.parseObject(userInfoResult);
            if ("false".equals(userInfoResultJSON.getString("success"))) {
                throw new Exception("dingtalk_app_user failed： " + userInfoResultJSON.getJSONObject("content").getBoolean("responseMessage"));
            }
            String userInfo = userInfoResultJSON.getJSONObject("content").getString("data");
            return userInfo;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    public static void main(String[] args) {
//        CallTestController callTestController = new CallTestController();
//        ExecutableClient executableClient = ExecutableClient.getInstance();
//        executableClient.setAccessKey("wenditest_public_work-s26nQveu");
//        executableClient.setSecretKey("Ar1n4Q2YxlV3gxa22712X3T0PK8d55Ry7GKZs6v9");
//        executableClient.setDomainName("openplatform-open.alibaba-inc.com");
//        executableClient.setProtocal("https");
//        executableClient.init();
//        PostClient postClient = executableClient.newPostClient("/rpc/oauth2/dingtalk_auth_code.json");
//        postClient.addParameter("corpid", "");//该参数是租户id，可以不传
//        postClient.addParameter("access_token", "cb6908cf23c7480ebc3868536300e600019adb01");//登录态的accesstoken，需要模拟移动端登录来获取
//        String codeResult = postClient.post();
//        System.out.println(codeResult);
//        JSONObject jsonObject = JSONObject.parseObject(codeResult);
//        String authCode = Optional.ofNullable(jsonObject)
//                .map(json -> json.getJSONObject("content"))
//                .map(content -> content.getJSONObject("data"))
//                .map(data -> data.getString("auth_code"))
//                .orElse(null);
//        System.out.println(callTestController.getUserInfo(authCode));
//    }
}
