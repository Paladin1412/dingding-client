//package com.alibaba.hj;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.hj.util.MainClient;
//import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
//import com.alibaba.xxpt.gateway.shared.client.http.GetClient;
//import com.alibaba.xxpt.gateway.shared.client.http.PostClient;
//
//import java.util.Optional;
//
///**
// * @Description TODO
// * @Author liangjixun
// * @Date 2020/3/26 下午11:47
// * @Version 1.0
// */
//public class WendiTestController {
//
//    ExecutableClient executableClient;
//    String appToken;
//
//    public static void main(String[] args) {
//        WendiTestController wendiTestController = new WendiTestController();
//        try {
//            wendiTestController.initClient();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        PostClient postClient = wendiTestController.executableClient.newPostClient("/rpc/oauth2/dingtalk_auth_code.json");
//        postClient.addParameter("corpid","");
//        postClient.addParameter("access_token", MainClient.getAccessToken());
//        String codeResult = postClient.post();
//        System.out.println("codeResult="+codeResult);
//        JSONObject jsonObject = JSONObject.parseObject(codeResult);
//        String authCode = Optional.ofNullable(jsonObject)
//                .map(json -> json.getJSONObject("content"))
//                .map(content -> content.getJSONObject("data"))
//                .map(data -> data.getString("auth_code"))
//                .orElse(null);
//        System.out.println("authCode="+wendiTestController.getUserInfo(authCode));
//        wendiTestController.executableClient.destroy();
//    }
//
//    private void initClient() throws Exception{
//        executableClient = ExecutableClient.getInstance();
//        executableClient.setAccessKey("wenditest_public_work-s26nQveu");
//        executableClient.setSecretKey("Ar1n4Q2YxlV3gxa22712X3T0PK8d55Ry7GKZs6v9");
//        executableClient.setDomainName("openplatform-open.alibaba-inc.com");
//        executableClient.setProtocal("https");
//        executableClient.init();
//    }
//
//    private String getAppToken() throws Exception{
//        GetClient getClient = executableClient.newGetClient("/gettoken.json");
//
//        String apiResult = getClient.get();
//        JSONObject jsonObject = JSONObject.parseObject(apiResult);
//        if(!jsonObject.getBoolean("success") || !jsonObject.getJSONObject("content").getBoolean("success")){
//            throw new Exception("get apptoken failed" + jsonObject.getJSONObject("content").getBoolean("responseMessage"));
//        }
//        return jsonObject.getJSONObject("content").getJSONObject("data").getString("accessToken");
//    }
//
//    public String getUserInfo(String authCode) {
//        try{
//
//            if(null == executableClient){
//                initClient();
//            }
//
//            appToken = getAppToken();
//
//            PostClient postClient = executableClient.newPostClient("/rpc/oauth2/dingtalk_app_user.json");
//            postClient.addParameter("access_token", "b578c5a26aac4a729278f124ab8f5600018ee601");
////            postClient.addParameter("auth_code", authCode);
//            postClient.addParameter("auth_code", "497c3aa4a6e64274b17f5f35db785f00018ee601");
//            String apiResult = postClient.post();
//            JSONObject jsonObject = JSONObject.parseObject(apiResult);
//            if(!jsonObject.getBoolean("success") || !jsonObject.getJSONObject("content").getBoolean("success")){
//                throw new Exception("dingtalk_app_user failed： " + jsonObject.getJSONObject("content").getBoolean("responseMessage"));
//            }
//
//            String userInfo = jsonObject.getJSONObject("content").getString("data");
//
//            return userInfo;
//
//        } catch(Exception e){
//            return null;
//        }
//    }
//
//}
