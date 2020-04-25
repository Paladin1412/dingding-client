package com.alibaba.hj.entity;

import java.util.ArrayList;

/**
 * @author chenhui
 * @version 1.0
 * @create 2020-04-11
 */
public class ZzdEventParam {

    /**
     * 状态值 A为正常  F为失效  TOTAL所有数据
     */
    public static final String ZZDSTATUS_NORMAL = "A";
    public static final String ZZDSTATUS_INVALID = "F";
    public static final String ZZDSTATUS_TOTAL = "TOTAL";

    /**
     * 任职状态  all所有  main主职  adjunct副职
     */
    public static final String EMPLOYEE_POSITION_TYPE_ALL = "EMPLOYEE_POSITION_TYPE_ALL";
    public static final String EMPLOYEE_POSITION_TYPE_MAIN = "EMPLOYEE_POSITION_TYPE_MAIN";
    public static final String EMPLOYEE_POSITION_TYPE_ADJUNCT = "EMPLOYEE_POSITION_TYPE_ADJUNCT";


    private String dispatchId;

    private String eventTag;

    private ZzdEventCallBack content;

    private ArrayList<ZzdUserOrg> infos;

    public ZzdEventParam() {
    }

    public ZzdEventParam(String dispatchId, String eventTag, ZzdEventCallBack content, ArrayList<ZzdUserOrg> infos) {
        this.dispatchId = dispatchId;
        this.eventTag = eventTag;
        this.content = content;
        this.infos = infos;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getEventTag() {
        return eventTag;
    }

    public void setEventTag(String eventTag) {
        this.eventTag = eventTag;
    }

    public ZzdEventCallBack getContent() {
        return content;
    }

    public void setContent(ZzdEventCallBack content) {
        this.content = content;
    }

    public ArrayList<ZzdUserOrg> getInfos() {
        return infos;
    }

    public void setInfos(ArrayList<ZzdUserOrg> infos) {
        this.infos = infos;
    }
}
