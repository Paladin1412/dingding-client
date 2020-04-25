package com.alibaba.hj.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.hj.entity.ZzdEventCallBack;
import com.alibaba.hj.entity.ZzdEventParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 浙政钉2.0订阅事件回调
 *
 * @author chenhui
 * @version 1.0
 * @create 2020-04-08
 */
@Controller
@RequestMapping("/api/zzdEvent")
public class ZzdEventServlet {

    private final static Logger logger = LoggerFactory.getLogger(ZzdEventServlet.class);

    /**
     * 组织新增或更新
     */
    private static final String EVENTTAG_ORG_ADDORUPDATE = "MOZI_VDS_TENANT_CHANGE|ORGANIZATION_ADD_UPDATE";
    /**
     * 父组织变动
     */
    private static final String EVENTTAG_ORG_PARENTCHANGE = "MOZI_VDS_TENANT_CHANGE|ORGANIZATION_PARENT_CHANGED";
    /**
     * 删除组织
     */
    private static final String EVENTTAG_ORG_DELETE = "MOZI_VDS_TENANT_CHANGE|ORGANIZATION_REMOVE";
    /**
     * 用户新增或更新
     */
    private static final String EVENTTAG_USER_ADDORUPDATE = "MOZI_VDS_TENANT_CHANGE|EMPLOYEE_ADD_UPDATE";
    /**
     * 员工离职
     */
    private static final String EVENTTAG_USER_LEAVE = "MOZI_VDS_TENANT_CHANGE|EMPLOYEE_LEAVE";
    /**
     * 关联用户
     */
    private static final String EVENTTAG_ORGANIZATION_ATTACH_EMPLOYEE = "MOZI_VDS_TENANT_CHANGE|ORGANIZATION_ATTACH_EMPLOYEE";
    /**
     * 取消关联用户
     */
    private static final String EVENTTAG_ORGANIZATION_DETACH_EMPLOYEE = "MOZI_VDS_TENANT_CHANGE|ORGANIZATION_DETACH_EMPLOYEE";

//    @Autowired
//    private ZzdEventService zzdEventService;
//
//    /**
//     * 组织新增或修改
//     * @param request
//     * @param response
//     * @param callBack
//     */
//    @RequestMapping(value = "/addOrUpdateOrg", method = RequestMethod.POST)
//    public void addOrUpdateOrg (HttpServletRequest request, HttpServletResponse response, ZzdEventParam callBack) {
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        logger.error("组织新增或修改的参数:{}", SerializerUtil.serialize(parameterMap));
//        String methodName = "浙政钉回调事件——组织新增或更新addOrUpdateOrg";
//        logger.error(methodName);
//        String record = SerializerUtil.serialize(callBack);
//        logger.error("回调方法执行,参数:{}", record);
//        //记录debug日志
//        LogUtils.recordDebugLog(methodName, record);
//        String eventTag = callBack.getEventTag();
//        // 第一次进行注册回调
//        if (StringUtils.isNotBlank(eventTag) &&  StringUtils.equals(EVENTTAG_ORG_ADDORUPDATE, eventTag)) {
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        ZzdEventCallBack content = callBack.getContent();
//        if (null != content) {
//            List<String> organizationCodes = content.getOrganizationCodes();
//            if (!CollectionUtils.isEmpty(organizationCodes)) {
//                for (String zzdorgcode : organizationCodes) {
//                    IErrorCode errorCode = zzdEventService.addOrUpdateOrg(zzdorgcode);
//                    if (ErrorCodeUtil.isFail(errorCode)) {
//                        // 当有一个执行失败时, 直接返回失败信息, 停止继续执行
//                        returnResult(response, errorCode.getCode(), errorCode.getMsg());
//                        return;
//                    }
//                }
//                returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//                return;
//            }
//        }
//        returnResult(response, ZzdOrgEvent.ZZDORGEVENT_OTHER.getCode(), ZzdOrgEvent.ZZDORGEVENT_OTHER.getMsg());
//    }
//
//    /**
//     * 组织父组织变动
//     * @param request
//     * @param response
//     * @param callBack
//     */
//    @RequestMapping(value = "/orgParentChange", method = RequestMethod.POST)
//    public void orgParentChange (HttpServletRequest request, HttpServletResponse response, ZzdEventParam callBack) {
//        String methodName = "浙政钉回调事件——组织父组织变动orgParentChange";
//        logger.error(methodName);
//        String record = SerializerUtil.serialize(callBack);
//        logger.error("回调方法执行,参数:{}", record);
//        //记录debug日志
//        LogUtils.recordDebugLog(methodName, record);
//        String eventTag = callBack.getEventTag();
//        // 第一次进行注册回调
//        if (StringUtils.isNotBlank(eventTag) &&  StringUtils.equals(EVENTTAG_ORG_PARENTCHANGE, eventTag)) {
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        ZzdEventCallBack content = callBack.getContent();
//
//        if (null != content) {
//            List<String> organizationCodes = content.getOrganizationCodes();
//            if (!CollectionUtils.isEmpty(organizationCodes)) {
//                for (String zzdorgcode : organizationCodes) {
//                    IErrorCode errorCode = zzdEventService.parentOrgChange(zzdorgcode);
//                    if (ErrorCodeUtil.isFail(errorCode)) {
//                        returnResult(response, errorCode.getCode(), errorCode.getMsg());
//                        return;
//                    }
//                }
//                returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//                return;
//            }
//        }
//        returnResult(response, ZzdOrgEvent.ZZDORGEVENT_OTHER.getCode(), ZzdOrgEvent.ZZDORGEVENT_OTHER.getMsg());
//    }
//
//    /**
//     * 删除组织
//     * @param request
//     * @param response
//     * @param callBack
//     */
//    @RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
//    public void deleteOrg (HttpServletRequest request, HttpServletResponse response, ZzdEventParam callBack) {
//        String methodName = "浙政钉回调事件——删除组织deleteOrg";
//        logger.error(methodName);
//        String record = SerializerUtil.serialize(callBack);
//        logger.error("回调方法执行,参数:{}", record);
//        //记录debug日志
//        LogUtils.recordDebugLog(methodName, record);
//        String eventTag = callBack.getEventTag();
//        // 第一次进行注册回调
//        if (StringUtils.isNotBlank(eventTag) &&  StringUtils.equals(EVENTTAG_ORG_DELETE, eventTag)) {
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        ZzdEventCallBack content = callBack.getContent();
//
//        if (null != content) {
//            List<String> organizationCodes = content.getOrganizationCodes();
//            if (!CollectionUtils.isEmpty(organizationCodes)) {
//                for (String zzdorgcode : organizationCodes) {
//                    IErrorCode errorCode = zzdEventService.deleteOrg(zzdorgcode);
//                    if (ErrorCodeUtil.isFail(errorCode)) {
//                        returnResult(response, errorCode.getCode(), errorCode.getMsg());
//                        return;
//                    }
//                }
//                returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//                return;
//            }
//        }
//        returnResult(response, ZzdOrgEvent.ZZDORGEVENT_OTHER.getCode(), ZzdOrgEvent.ZZDORGEVENT_OTHER.getMsg());
//    }

    /**
     * 用户更新或新增
     * @param request
     * @param response
     * @param callBack
     */
    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
    public String userAddOrUpdate (@RequestBody String param, HttpServletResponse response, ZzdEventParam callBack) {
        String methodName = "浙政钉回调事件——用户更新或新增userAddOrUpdate";
        logger.warn("add event execute [{}]", param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("errcode", "0");
        jsonObject.put("errmsg", "成功");
        return jsonObject.toJSONString();
//        logger.error(methodName);
//        String record = SerializerUtil.serialize(callBack);
//        logger.error("回调方法执行,参数:{}", record);
//        //记录debug日志
//        LogUtils.recordDebugLog(methodName, record);
//        String eventTag = callBack.getEventTag();
//        // 第一次进行注册回调
//        if (StringUtils.isNotBlank(eventTag) &&  StringUtils.equals(EVENTTAG_USER_ADDORUPDATE, eventTag)) {
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        ZzdEventCallBack content = callBack.getContent();
//
//        if (null != content) {
//            List<String> employeeCodes = content.getEmployeeCodes();
//            if (!CollectionUtils.isEmpty(employeeCodes)) {
//                for (String zzdusercode : employeeCodes) {
//                    IErrorCode errorCode = zzdEventService.addOrUpdateUser(zzdusercode);
//                    if (ErrorCodeUtil.isFail(errorCode)) {
//                        returnResult(response, errorCode.getCode(), errorCode.getMsg());
//                        return;
//                    }
//                }
//                returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//                return;
//            }
//        }
//        returnResult(response, ZzdOrgEvent.ZZDORGEVENT_OTHER.getCode(), ZzdOrgEvent.ZZDORGEVENT_OTHER.getMsg());
    }

//
//    /**
//     * 员工离职
//     * @param request
//     * @param response
//     * @param callBack
//     */
//    @RequestMapping(value = "/userLevel", method = RequestMethod.POST)
//    public void userLevel (HttpServletRequest request, HttpServletResponse response, ZzdEventParam callBack) {
//        String methodName = "浙政钉回调事件——员工离职userLevel";
//        logger.error(methodName);
//        String record = SerializerUtil.serialize(callBack);
//        logger.error("回调方法执行,参数:{}", record);
//        //记录debug日志
//        LogUtils.recordDebugLog(methodName, record);
//        String eventTag = callBack.getEventTag();
//        // 第一次进行注册回调
//        if (StringUtils.isNotBlank(eventTag) &&  StringUtils.equals(EVENTTAG_USER_LEAVE, eventTag)) {
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        ZzdEventCallBack content = callBack.getContent();
//
//        if (null != content) {
//            List<String> employeeCodes = content.getEmployeeCodes();
//            if (!CollectionUtils.isEmpty(employeeCodes)) {
//                for (String zzdusercode : employeeCodes) {
//                    IErrorCode errorCode = zzdEventService.userLevel(zzdusercode);
//                    if (ErrorCodeUtil.isFail(errorCode)) {
//                        returnResult(response, errorCode.getCode(), errorCode.getMsg());
//                        return;
//                    }
//                }
//                returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//                return;
//            }
//        }
//        returnResult(response, ZzdOrgEvent.ZZDORGEVENT_OTHER.getCode(), ZzdOrgEvent.ZZDORGEVENT_OTHER.getMsg());
//    }
//
//    /**
//     * 组织关联员工
//     * @param request
//     * @param response
//     * @param callBack
//     */
//    @RequestMapping(value = "/attachUser", method = RequestMethod.POST)
//    public void attachUser (HttpServletRequest request, HttpServletResponse response, ZzdEventParam callBack) {
//        String methodName = "浙政钉回调事件——组织关联员工attachUser";
//        logger.error(methodName);
//        String record = SerializerUtil.serialize(callBack);
//        logger.error("回调方法执行,参数:{}", record);
//        //记录debug日志
//        LogUtils.recordDebugLog(methodName, record);
//        String eventTag = callBack.getEventTag();
//        // 第一次进行注册回调
//        if (StringUtils.isNotBlank(eventTag) &&  StringUtils.equals(EVENTTAG_ORGANIZATION_ATTACH_EMPLOYEE, eventTag)) {
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        ArrayList<ZzdUserOrg> infos = callBack.getInfos();
//
//        if (!CollectionUtils.isEmpty(infos)) {
//            // 组织关联员工
//            for (ZzdUserOrg zzdUserOrg : infos) {
//                String employeeCode = zzdUserOrg.getEmployeeCode();
//                String organizationCode = zzdUserOrg.getOrganizationCode();
//                IErrorCode errorCode = zzdEventService.attachUser(organizationCode, employeeCode);
//                if (ErrorCodeUtil.isFail(errorCode)) {
//                    returnResult(response, errorCode.getCode(), errorCode.getMsg());
//                    return;
//                }
//            }
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        returnResult(response, ZzdOrgEvent.ZZDORGEVENT_OTHER.getCode(), ZzdOrgEvent.ZZDORGEVENT_OTHER.getMsg());
//    }
//
//    /**
//     * 组织取关员工
//     * @param request
//     * @param response
//     * @param callBack
//     */
//    @RequestMapping(value = "/detachUser", method = RequestMethod.POST)
//    public void detachUser (HttpServletRequest request, HttpServletResponse response, ZzdEventParam callBack) {
//        String methodName = "浙政钉回调事件——组织取关员工detachUser";
//        logger.error(methodName);
//        String record = SerializerUtil.serialize(callBack);
//        logger.error("回调方法执行,参数:{}", record);
//        //记录debug日志
//        LogUtils.recordDebugLog(methodName, record);
//        String eventTag = callBack.getEventTag();
//        // 第一次进行注册回调
//        if (StringUtils.isNotBlank(eventTag) &&  StringUtils.equals(EVENTTAG_ORGANIZATION_DETACH_EMPLOYEE, eventTag)) {
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        ArrayList<ZzdUserOrg> infos = callBack.getInfos();
//
//        if (!CollectionUtils.isEmpty(infos)) {
//            // 组织取消关联员工
//            for (ZzdUserOrg zzdUserOrg : infos) {
//                String employeeCode = zzdUserOrg.getEmployeeCode();
//                String organizationCode = zzdUserOrg.getOrganizationCode();
//                IErrorCode errorCode = zzdEventService.detachUser(organizationCode, employeeCode);
//                if (ErrorCodeUtil.isFail(errorCode)) {
//                    returnResult(response, errorCode.getCode(), errorCode.getMsg());
//                    return;
//                }
//            }
//            returnResult(response, ZzdOrgEvent.SUCCESS.getCode(), ZzdOrgEvent.SUCCESS.getMsg());
//            return;
//        }
//        returnResult(response, ZzdOrgEvent.ZZDORGEVENT_OTHER.getCode(), ZzdOrgEvent.ZZDORGEVENT_OTHER.getMsg());
//    }
//
//    private void returnResult(HttpServletResponse response, String errcode, String errmsg){
//
//        logger.error("浙政钉回调事件返回结果, errcode={}, errmsg={}",errcode,errmsg);
//    }



}
