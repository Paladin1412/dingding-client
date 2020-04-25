package com.alibaba.hj.entity;

/**
 * @author chenhui
 * @version 1.0
 * @create 2020-04-13
 */
public class ZzdUserOrg {

    /**
     * 组织code
     */
    private String organizationCode;
    /**
     * 是否是主职
     */
    private Boolean mainJob;
    /**
     * 是否是主职
     */
    private Boolean isChief;
    /**
     * 任职状态
     */
    private String status;
    /**
     * 用户code
     */
    private String employeeCode;

    /**
     * 人员在组织内的排序
     */
    private Long orderInOrganization;

    /**
     * 职务
     */
    private String govEmpPosJob;

    /**
     * 传真
     */
    private String empPosFaxNo;

    /**
     * 办公电话
     */
    private String govEmpPosPhoneNo;

    public ZzdUserOrg() {
    }

    public ZzdUserOrg(String organizationCode, Boolean mainJob, Boolean isChief, String status, String employeeCode, Long orderInOrganization, String govEmpPosJob, String empPosFaxNo, String govEmpPosPhoneNo) {
        this.organizationCode = organizationCode;
        this.mainJob = mainJob;
        this.isChief = isChief;
        this.status = status;
        this.employeeCode = employeeCode;
        this.orderInOrganization = orderInOrganization;
        this.govEmpPosJob = govEmpPosJob;
        this.empPosFaxNo = empPosFaxNo;
        this.govEmpPosPhoneNo = govEmpPosPhoneNo;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Boolean getMainJob() {
        return mainJob;
    }

    public void setMainJob(Boolean mainJob) {
        this.mainJob = mainJob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Boolean getChief() {
        return isChief;
    }

    public void setChief(Boolean chief) {
        isChief = chief;
    }

    public Long getOrderInOrganization() {
        return orderInOrganization;
    }

    public void setOrderInOrganization(Long orderInOrganization) {
        this.orderInOrganization = orderInOrganization;
    }

    public String getGovEmpPosJob() {
        return govEmpPosJob;
    }

    public void setGovEmpPosJob(String govEmpPosJob) {
        this.govEmpPosJob = govEmpPosJob;
    }

    public String getEmpPosFaxNo() {
        return empPosFaxNo;
    }

    public void setEmpPosFaxNo(String empPosFaxNo) {
        this.empPosFaxNo = empPosFaxNo;
    }

    public String getGovEmpPosPhoneNo() {
        return govEmpPosPhoneNo;
    }

    public void setGovEmpPosPhoneNo(String govEmpPosPhoneNo) {
        this.govEmpPosPhoneNo = govEmpPosPhoneNo;
    }
}
