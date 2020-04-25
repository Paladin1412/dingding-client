package com.alibaba.hj.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 回调参数
 *
 * @author chenhui
 * @version 1.0
 * @create 2020-04-09
 */
public class ZzdEventCallBack {

    /**
     * 数据id
     */
    private Long id;
    /**
     * 事件tag
     */
    private String eventTag;
    /**
     * 回调时间  毫秒值
     */
    private Long eventTime;
    /**
     * 订阅类别
     */
    private String tag;
    /**
     * 订阅主题
     */
    private String topic;
    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 组织code列表
     */
    private List<String> organizationCodes;
    /**
     * 员工code列表
     */
    private List<String> employeeCodes;
    /**
     * 回调数据
     */
    private String callbackData;
    /**
     * 回调时间 格式:2020-04-10 09:23
     */
    private String eventTimeStr;
    /**
     * 消息回调url
     */
    private String callbackUrl;

    public ZzdEventCallBack() {
    }

    public ZzdEventCallBack(Long id, String eventTag, Long eventTime, String tag, String topic, Long tenantId, List<String> organizationCodes, List<String> employeeCodes, String callbackData, String eventTimeStr, String callbackUrl) {
        this.id = id;
        this.eventTag = eventTag;
        this.eventTime = eventTime;
        this.tag = tag;
        this.topic = topic;
        this.tenantId = tenantId;
        this.organizationCodes = organizationCodes;
        this.employeeCodes = employeeCodes;
        this.callbackData = callbackData;
        this.eventTimeStr = eventTimeStr;
        this.callbackUrl = callbackUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventTag() {
        if (StringUtils.isBlank(eventTag) && StringUtils.isNotBlank(tag) && StringUtils.isNotBlank(topic)) {
            eventTag = topic + "|" + tag;
        }
        return eventTag;
    }

    public void setEventTag(String eventTag) {
        this.eventTag = eventTag;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getTag() {
        if (StringUtils.isNotBlank(eventTag) && StringUtils.isBlank(tag)) {
            String[] temp = eventTag.split("\\|");
            if (temp.length == 2) {
                tag = temp[1];
            }
        }
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<String> getOrganizationCodes() {
        return organizationCodes;
    }

    public void setOrganizationCodes(List<String> organizationCodes) {
        this.organizationCodes = organizationCodes;
    }

    public List<String> getEmployeeCodes() {
        return employeeCodes;
    }

    public void setEmployeeCodes(List<String> employeeCodes) {
        this.employeeCodes = employeeCodes;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    public String getTopic() {
        if (StringUtils.isNotBlank(eventTag) && StringUtils.isBlank(topic)) {
            topic =  eventTag.split("\\|")[0];
        }
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEventTimeStr() {
        return eventTimeStr;
    }

    public void setEventTimeStr(String eventTimeStr) {
        this.eventTimeStr = eventTimeStr;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
