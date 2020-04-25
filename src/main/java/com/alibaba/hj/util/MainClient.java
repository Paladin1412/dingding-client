package com.alibaba.hj.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.GetClient;
import com.alibaba.xxpt.gateway.shared.client.http.PostClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by issuser on 2020/3/25.
 */
public class MainClient {
    public static Logger logger = LoggerFactory.getLogger(MainClient.class);
    public static ExecutableClient executableClient = ExecutableClient.getInstance();

    static {
        //测试租户
//        executableClient.setAccessKey("ZZJ-risen-lst-XgZyWHEb43qy2eIf");
//        executableClient.setSecretKey("6ZxCy62DK0nNCa198gk4g73ku1xyloobPbbZVyur");
//        executableClient.setAccessKey("ZZJ-hzll-10002-N3y5665s67RZ36b");
//        executableClient.setSecretKey("h040Kjl5sL4xRPb63Ua7LU2p0QHXra1lGMW7E01D");
//        executableClient.setAccessKey("ZZJ-hzyh-idm-test-AquaDMNnuimu");
//        executableClient.setSecretKey("GVj0mWF59yU7s8Xq4X90f0aydknOUc640j6167J3");
//        //正式租户
//        executableClient.setAccessKey("ZZJ-risen-ceshi003-rE8Y1uMfeym");
//        executableClient.setSecretKey("nvQ26p7oV01wPzh349W11755um4xb808C6F0bLvm");
//        executableClient.setAccessKey("zzd-idaas-sync-VYT6pDI08nUbiam");
//        executableClient.setSecretKey("ih44946C2uG0IL88B9X6RPvYqIru0QQQ399BR96u");//
// 线上
        executableClient.setAccessKey("personnel-20200424-c-wqmriQFjy");
        executableClient.setSecretKey("37iX57657Y1X6Lkg23e63q661F2DuaXA3m59smmH");
//        executableClient.setAccessKey("zzd-idaas-sync-VYT6pDI08nUbiam");
//        executableClient.setSecretKey("ih44946C2uG0IL88B9X6RPvYqIru0QQQ399BR96u");
        //// 公有云测试
//        executableClient.setAccessKey("remote-test-0P199w3mEouPVBBnae");
//        executableClient.setSecretKey("7dc7k0fDMG0h0kJnl3r75S5Cbts4NxNk6M69GU63");
//        executableClient.setAccessKey("dev-test-nu47ntCAhuD3344yMfMzu");
//        executableClient.setSecretKey("P5H9O9w0kvldQ92Tufru65ZS9u75m81kjFC0pfF3");
//        executableClient.setDomainName("openplatform-open.alibaba-inc.com");//公有云
//        executableClient.setDomainName("openplatform-pro.ding.zj.gov.cn");//专有云
//        executableClient.setDomainName("openplatform.alibaba-inc.com");//saas
        executableClient.setDomainName("openplatform.dg-work.cn");//线上
        executableClient.setProtocal("https");
        executableClient.init();
    }

    public static void main(String[] args) {
//        String tenantId="34748";//公有云租户
        String tenantId = "196729";//正式租户
//        String tenantId = "187059";//测试租户
        String recevingIds = "69524588,69526567,69527234,11865562";
        String rootOrganizationCode = "0f0f686f-57f7-4c44-b6c0-bf10805f0433";
        String yhOrganizationCode = "GO_cc2c6452027f4484bd438307700d7bae";
        String yhCompanyOrgCode = "GO_fa185942c5284828b0d3e402dae8ab28";
        String organizationCode = "GO_2502acbd4c04480f8cc29eeb80c35735";
        String organizationCodes = "GO_division100037130815860319828370";
        String employeeCode = "GE_3410eaabf6ed4248ae967a7d48d50b1a";
        String employeeCodes = "GE_538ce3c237944d1db013cbe1abf901d0";
        String[] yhEmployeeCodes = new String[]{"GE_97e87a514c3e4b13abd2b4fad66bb697", "GE_fd3f8c3eaa404d308c4f1a2b17242ffe", "GE_d32ba4c74e294c2c9102c950ef46f2fc", "GE_3daaf2fdbd134253b8a34b08dfaa6ca7"};
        String[] ecodes = new String[]{employeeCode, employeeCodes};
        String[] ocodes = new String[]{organizationCode, organizationCodes, yhOrganizationCode, yhCompanyOrgCode};
//        getAuthCode();
        getAccessToken();
//        getJsapiTicket();
//        workNotification(tenantId, recevingIds);
//        register_event_callback();
//        update_event_callback_define();
//        delete_event_callback_define();
//        retry_callback_failed();
//        remove_callback_failed();
//        query_callback();
//        getRootOrganization();
//        getOrganizationByCode(tenantId, yhCompanyOrgCode);
//        pageSubOrganizationCodes(tenantId, yhOrganizationCode);
//        listOrganizationsByCodes(tenantId, ocodes);
//        pageOrganizationEmployeeCodes(tenantId, yhCompanyOrgCode);
//        listOrgEmployeePositionsByCodes(tenantId, organizationCode, ecodes);
//        listEmployeePositionsByEmployeeCode(tenantId, employeeCodes);
//        listEmployeeAccountIds(tenantId, ecodes);
//        getEmployeeByCode(tenantId, employeeCode);
//        listEmployeesByCodes(tenantId, ecodes);
//        listGovCellPhoneNumberByCodes(tenantId, yhEmployeeCodes);
//        logger.warn("accessToken=" + getAccessToken());
//        getAuthCode();
//        logger.warn("userInfo=" + getUserInfo("1396025323e946668842f5639b490e00b50dda01"));
//        getAccountSensitiveDataByAccount();
    }

    public static String getAuthCode() {
        //executableClient要单例，并且使用前要初始化，只需要初始化一次
        String api = "/rpc/oauth2/dingtalk_auth_code.json";
        PostClient postClient = executableClient.newPostClient(api);
        //设置参数
        postClient.addParameter("access_token", getAccessToken());
//        postClient.addParameter("access_token", "a65ef2f82be34c7191e8dd3514c1c700019b3e01");
        //调用API
        String apiResult = postClient.post();
        logger.warn("getAuthCode=" + apiResult);
        JSONObject jsonObject = JSONObject.parseObject(apiResult);
        String authCode = Optional.ofNullable(jsonObject)
                .map(json -> json.getJSONObject("content"))
                .map(content -> content.getJSONObject("data"))
                .map(data -> data.getString("auth_code"))
                .orElse(null);
        return authCode;
    }

    public static String getAccountSensitiveDataByAccount() {
        //executableClient要单例，并且使用前要初始化，只需要初始化一次
        String api = "/rpc/account/getAccountSensitiveDataByAccount";
        PostClient postClient = executableClient.newPostClient(api);
        //设置参数
        postClient.addParameter("namespace", "local");
        postClient.addParameter("account", "df30178141221");
        postClient.addParameter("name", "梅中杰");
        postClient.addParameter("deviceId", "12323123123");
//        postClient.addParameter("access_token", "a65ef2f82be34c7191e8dd3514c1c700019b3e01");
        //调用API
        String apiResult = postClient.post();
        logger.warn("getAccountSensitiveDataByAccount=" + apiResult);
        JSONObject jsonObject = JSONObject.parseObject(apiResult);
        String mobile = Optional.ofNullable(jsonObject)
                .map(json -> json.getJSONObject("content"))
                .map(content -> content.getJSONObject("data"))
                .map(data -> data.getString("mobile"))
                .orElse(null);
        return mobile;
    }


    public static String getUserInfo(String code) {
//        String str = "{\"success\":true,\"content\":{\"data\":{\"accountId\":289563688,\"lastName\":\"陈佳明\",\"clientId\":\"after-soso\",\"realmId\":58100,\"openid\":\"dc8b7185837766b2b7910471670b055d\",\"realmName\":\"浙政钉测试专有租户\",\"namespace\":\"local\",\"nickNameCn\":\"陈佳明\",\"tenantUserId\":\"58100$289563688\",\"account\":\"cjmceshizhuanyong\",\"employeeCode\":\"GE_c30ffe5264aa4a5fa1fccfc7d0694dba\"},\"success\":true,\"responseMessage\":\"成功\",\"responseCode\":\"0\"}}";
        String str = "{\"success\":true,\"content\":{\"data\":{\"accountId\":105437,\"lastName\":\"陈鸿坚\",\"clientId\":\"dev-test\",\"realmId\":34748,\"openid\":\"8224495af633f1814980ab2fbc5a9ce9\",\"realmName\":\"陈鸿坚测试三方isv\",\"namespace\":\"local\",\"nickNameCn\":\"陈鸿坚\",\"tenantUserId\":\"34748$105437\",\"account\":\"afterzz-chj\",\"employeeCode\":\"GE_ad1c9f47f6704a5fb9c04a64407d8c20\"},\"success\":true,\"responseMessage\":\"成功\",\"responseCode\":\"0\"}}";
        try {
            //executableClient要单例，并且使用前要初始化，只需要初始化一次
            String api = "/rpc/oauth2/dingtalk_app_user.json";
//            String api = "/user/get";
            PostClient postClient = executableClient.newPostClient(api);
            //设置参数
            postClient.addParameter("access_token", getAccessToken());
            if (StringUtils.isNotBlank(code)) {
                postClient.addParameter("auth_code", code);
            } else {
                postClient.addParameter("auth_code", getAuthCode());
            }
            //调用API
            String apiResult = postClient.post();
            return apiResult;
        } catch (Exception e) {
            logger.warn("error:" + e.getMessage());
            return str;
        }
    }

    public static String getAccessToken() {
        //executableClient要单例，并且使用前要初始化，只需要初始化一次
        String api = "/gettoken.json";
//        String api = "/rpc/oauth2/dingtalk_app_token.json";
        GetClient getClient = executableClient.newGetClient(api);
        //设置参数
        getClient.addParameter("appkey", "personnel-20200424-c-wqmriQFjy");
        getClient.addParameter("appsecret", "37iX57657Y1X6Lkg23e63q661F2DuaXA3m59smmH");
        //调用API
        String apiResult = getClient.get();
        logger.warn("getAccessToken{}", apiResult);
        JSONObject jsonObject = JSONObject.parseObject(apiResult);
        String accessToken = Optional.ofNullable(jsonObject)
                .map(json -> json.getJSONObject("content"))
                .map(content -> content.getJSONObject("data"))
                .map(data -> data.getString("accessToken"))
                .orElse(null);
        return accessToken;
    }

    public static String getJsapiTicket() {
        //executableClient要单例，并且使用前要初始化，只需要初始化一次
        String api = "/get_jsapi_ticket";
        GetClient getClient = executableClient.newGetClient(api);
        //设置参数
        getClient.addParameter("access_token", getAccessToken());
        //调用API
        String apiResult = getClient.get();
        logger.warn("getJsapiTicket=" + apiResult);
        JSONObject jsonObject = JSONObject.parseObject(apiResult);
//        String ticket = Optional.ofNullable(jsonObject)
//                .map(json -> json.getJSONObject("content"))
//                .map(content -> content.getJSONObject("data"))
//                .map(data -> data.getString("accessToken"))
//                .orElse(null);
        return apiResult;
    }

    public static String getRootOrganization() {
        String api = "/mozi/organization/getRootOrganization";
        PostClient postClient = executableClient.newPostClient(api);
//        postClient.addParameter("tenantId", "187059");
//        postClient.addParameter("tenantId", "34748");
        postClient.addParameter("tenantId", "196729");
        //调用API
        String apiResult = postClient.post();
        logger.warn("rootOrganizationResult=" + apiResult);
        return apiResult;
    }

    public static String pageSubOrganizationCodes(String val1, String val2) {
        String api = "/mozi/organization/pageSubOrganizationCodes";

        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
//        postClient.addParameter("organizationCode", val2);
        postClient.addParameter("organizationCode", "GO_62676c26f70347e9bb6ee925c0ad3b48");
        //调用API
        String apiResult = postClient.post();
        logger.warn("subOrganizationCodesResult=" + apiResult);
        return apiResult;
    }

    public static String getOrganizationByCode(String val1, String val2) {
        String api = "/mozi/organization/getOrganizationByCode";

        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
//        postClient.addParameter("organizationCode", val2);
        postClient.addParameter("organizationCode", "GO_62676c26f70347e9bb6ee925c0ad3b48");
        //调用API
        String apiResult = postClient.post();
        logger.warn("getOrganizationByCode=" + apiResult);
        return apiResult;
    }

    public static String listOrganizationsByCodes(String val1, String... organizationCodes) {
        String api = "/mozi/organization/listOrganizationsByCodes";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
        for (String code : organizationCodes) {
            postClient.addParameter("organizationCodes", code);
        }
        //调用API
        String apiResult = postClient.post();
        logger.warn("listOrganizationsByCodes=" + apiResult);
        return apiResult;
    }

    public static String listEmployeePositionsByEmployeeCode(String val1, String val2) {
        String api = "/mozi/employee/listEmployeePositionsByEmployeeCode";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
        postClient.addParameter("employeeCode", val2);
        //调用API
        String apiResult = postClient.post();
        logger.warn("listEmployeePositionsByEmployeeCode=" + apiResult);
        return apiResult;
    }

    public static String pageOrganizationEmployeeCodes(String val1, String val2) {
        String api = "/mozi/organization/pageOrganizationEmployeeCodes";

        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
        postClient.addParameter("organizationCode", val2);
        //调用API
        String apiResult = postClient.post();
        logger.warn("pageOrganizationEmployeeCodes=" + apiResult);
        return apiResult;
    }

    public static String listEmployeeAccountIds(String val1, String... employeeCodes) {
        String api = "/mozi/employee/listEmployeeAccountIds";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
        for (String code : employeeCodes) {
            postClient.addParameter("employeeCodes", code);

        }
        //调用API
        String apiResult = postClient.post();
        logger.warn("listEmployeeAccountIds=" + apiResult);
        return apiResult;
    }

    public static String getEmployeeByCode(String val1, String val2) {
        String api = "/mozi/employee/getEmployeeByCode";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
        postClient.addParameter("employeeCode", val2);
        //调用API
        String apiResult = postClient.post();
        logger.warn("getEmployeeByCode=" + apiResult);
        return apiResult;
    }

    public static String listEmployeesByCodes(String val1, String... employeeCodes) {
        String api = "/mozi/employee/listEmployeesByCodes";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
        for (String code : employeeCodes) {
            postClient.addParameter("employeeCodes", code);

        }
        //调用API
        String apiResult = postClient.post();
        logger.warn("listEmployeesByCodes=" + apiResult);
        return apiResult;
    }

    public static String listOrgEmployeePositionsByCodes(String val1, String organizationCode, String... employeeCodes) {
        String api = "/mozi/employee/listOrgEmployeePositionsByCodes";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", val1);
//        postClient.addParameter("organizationCode", organizationCode);
        postClient.addParameter("organizationCode", "GO_contact1000446059158604204644200");
        for (String code : employeeCodes) {
            postClient.addParameter("employeeCodes", code);
        }
        //调用API
        String apiResult = postClient.post();
        logger.warn("listOrgEmployeePositionsByCodes=" + apiResult);
        return apiResult;
    }

    public static String listGovCellPhoneNumberByCodes(String tenantId, String... employeeCodes) {
        String api = "/mozi/employee/listGovCellPhoneNumberByCodes";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", tenantId);
        for (String code : employeeCodes) {
            postClient.addParameter("employeeCodes", code);
        }
        //调用API
        String apiResult = postClient.post();
        logger.warn("listGovCellPhoneNumberByCodes=" + apiResult);
        return apiResult;
    }

    public static String workNotification(String tenantId, String receiverIds) {
        String str = "{\n" +
                "    \"msgtype\":\"action_card\",\n" +
                "    \"action_card\":{\n" +
                "        \"title\":\"是透出到会话列表和通知的文案\",\n" +
                "        \"markdown\":\"支持markdown格式的正文内容\",\n" +
                "        \"btn_orientation\":\"1\",\n" +
                "        \"btn_json_list\":[\n" +
                "            {\n" +
                "                \"title\":\"一个按钮\",\n" +
                "                \"action_url\":\"https://www.taobao.com\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"title\":\"两个按钮\",\n" +
                "                \"action_url\":\"https://www.tmall.com\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"content\":\"大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本\",\n" +
                "    \"image\":\"$iwHRA-gKAAvSAANkYAKkanBlZwMCBNELQAXRA7QGsIB8Yz-prMW2Ag1UJRP3DXgHAAgACaEw\",\n" +
                "    \"file_count\":\"3\",\n" +
                "    \"author\":\"李四 \"\n" +
                "}";
        JSONObject msg = new JSONObject();
        JSONObject text = new JSONObject();
        text.put("content", str);
        msg.put("msgtype", "text");
        msg.put("text", text);
//        logger.warn("message=" + msg.toJSONString());
        String api = "/message/workNotification";
//        String api = "/message/announcement";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("tenantId", tenantId);
        postClient.addParameter("receiverIds", receiverIds);
        postClient.addParameter("msg", str);
//        postClient.addParameter("msg", str);
        //调用API
        String apiResult = postClient.post();
        logger.warn("codeResult=" + apiResult);
        return apiResult;
    }

    public static String query_callback() {
//        String api = "/openplatform/message/query_callback_failed";
        String api = "/openplatform/message/query_callback_define";
        PostClient postClient = executableClient.newPostClient(api);
//        postClient.addParameter("eventTag", "MOZI_VDS_TENANT_CHANGE|ORGANIZATION_ADD_UPDATE");
//        postClient.addParameter("callbackUrl", "https://openplatform-pro.ding.zj.gov.cn/test");
        //调用API
        String apiResult = postClient.post();
        logger.warn("query_callback=" + apiResult);
        return apiResult;
    }

    public static String register_event_callback() {
        String api = "/openplatform/message/register_event_callback";
        PostClient postClient = executableClient.newPostClient(api);
//        postClient.addParameter("eventTag", "MOZI_VDS_TENANT_CHANGE|EMPLOYEE_ADD_UPDATE");
//        postClient.addParameter("callbackUrl", "http://119.3.12.87:8090/rpc/delete");
//        postClient.addParameter("callbackUrl", "http://115.236.188.138:8094/yhidm/api/zzdEvent/addOrUpdateUser");

        postClient.addParameter("eventTag", "MOZI_VDS_TENANT_CHANGE|ORGANIZATION_ADD_UPDATE");
        postClient.addParameter("callbackUrl", "http://119.3.12.87:8090/rpc/update");
//        postClient.addParameter("callbackUrl", "http://115.236.188.138:8094/yhidm/rpc/update");
//        postClient.addParameter("callbackUrl", "http://119.3.12.87:8090/api/zzdEvent/addOrUpdateUser");
//        postClient.addParameter("msg", msg.toJSONString());
        //调用API
        String apiResult = postClient.post();
        logger.warn("register_event_callback=" + apiResult);
        return apiResult;
    }

    public static String update_event_callback_define() {
        String api = "openplatform/message/update_event_callback_define";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("eventCallbackId", "282");
        postClient.addParameter("callbackUrl", "http://119.3.12.87:8090/rpc/add");
//        postClient.addParameter("msg", msg.toJSONString());
        //调用API
        String apiResult = postClient.post();
        logger.warn("update_event_callback_define=" + apiResult);
        return apiResult;
    }

    public static String delete_event_callback_define() {
        String api = "/openplatform/message/delete_event_callback_define";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("eventCallbackId", "302");
        //调用API
        String apiResult = postClient.post();
        logger.warn("delete_event_callback_define=" + apiResult);
        return apiResult;
    }

    public static String retry_callback_failed() {
//        String api = "/openplatform/message/register_event_callback";
        String api = "/openplatform/message/retry_callback_failed";
//        String api = "/openplatform/message/query_callback_define";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("eventCallbackFailedId", "4445657");
//        postClient.addParameter("callbackUrl", "https://openplatform-pro.ding.zj.gov.cn/test");
        //调用API
        String apiResult = postClient.post();
        logger.warn("retry_callback_failed=" + apiResult);
        return apiResult;
    }

    public static String remove_callback_failed() {
        String api = "/openplatform/message/remove_callback_failed";
        PostClient postClient = executableClient.newPostClient(api);
        postClient.addParameter("eventCallbackFailedId", "4445657");
        //调用API
        String apiResult = postClient.post();
        logger.warn("remove_callback_failed=" + apiResult);
        return apiResult;
    }

    public void fastJsonDemo() {
    }
}
